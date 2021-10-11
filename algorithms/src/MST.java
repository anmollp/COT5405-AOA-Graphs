import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;
import java.util.Random;

import java.util.*;

import static java.lang.System.nanoTime;


class MST {
    private final int nodes;
    private final int edges;
    private static Graph<Integer, DefaultWeightedEdge> g;
    private DefaultWeightedEdge maxEdge;

    public MST(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph<Integer, DefaultWeightedEdge>  createGraph() {
        GraphGenerator<Integer, DefaultWeightedEdge, Integer> gen = new GnmRandomGraphGenerator<>(nodes, edges);
        g = new DefaultUndirectedWeightedGraph<>(SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier());
        gen.generateGraph(g);
        return g;
    }

    public void setWeightsForGraph() {
        for (DefaultWeightedEdge e : g.edgeSet()) {
            g.setEdgeWeight(e, getRandomWeightBetween(3,12));
        }
    }

    public int getRandomWeightBetween(int min, int max) {
        Random rand = new Random();
        int upperBound = max - min + 1;
        return min + rand.nextInt(upperBound);
    }

    public Boolean isCyclicUtil(int v, int parent, Boolean[] visited, DefaultWeightedEdge maxEdge) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (DefaultWeightedEdge edge : g.edgesOf(v)) {

            int neighbor = g.getEdgeSource(edge);
            if(neighbor == v)
                neighbor = g.getEdgeTarget(edge);
            if( g.getEdgeWeight(maxEdge) <= g.getEdgeWeight(edge)) maxEdge = edge;
            if (!visited[neighbor]) {
                if (isCyclicUtil(neighbor, v, visited, maxEdge)) return true;
            }
            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (neighbor != parent) {
                g.removeEdge(maxEdge);
                return true;
            }
        }
        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    public Boolean hasCycle() {
        for(DefaultWeightedEdge e: g.edgeSet()){
            maxEdge = e;
            break;
        }

        // Mark all the vertices as not visited and not part of recursion stack
        Boolean[] visited = new Boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to detect cycle in different DFS trees
        for (int node : g.vertexSet()) {
            // Don't recur for u if already visited
            if (!visited[node]) {
                if(isCyclicUtil(node, -1, visited, maxEdge)) return true;
            }
        }
        return false;
    }

    public double getKruskalMSTWeight(Graph<Integer, DefaultWeightedEdge> graph) {
        SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> x =
                new KruskalMinimumSpanningTree<>(graph).getSpanningTree();
        return x.getWeight();
    }

    public double getMinimumSpanningTreeWeight() {
        double totalWeight = 0;
        for(DefaultWeightedEdge e: g.edgeSet()) {
            totalWeight += g.getEdgeWeight(e);
        }
        return totalWeight;
    }
}

class MSTClient {
    public static void main(String[] args) {
        int[] testVertices = new int[]{10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};
        MST[] testCase = new MST[testVertices.length];
        for(int i=0; i< testVertices.length; i++){
            testCase[i] = new MST(testVertices[i], testVertices[i]+8);
        }
        for(int j=0;j< testCase.length;j++) {
            Graph<Integer, DefaultWeightedEdge> graph = testCase[j].createGraph();
            testCase[j].setWeightsForGraph();
            long start = nanoTime();
            while (testCase[j].hasCycle()) {
            }
            long finish = nanoTime();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed + " nanoseconds elapsed in computing MST for " +
                    testVertices[j] + " vertices" + " and " + (testVertices[j] + 8) + " edges");
            //Test case validation
            if (testCase[j].getMinimumSpanningTreeWeight() != testCase[j].getKruskalMSTWeight(graph))
                throw new AssertionError();
        }

    }
}