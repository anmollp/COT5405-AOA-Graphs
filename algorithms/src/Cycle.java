import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.util.SupplierUtil;

import java.util.ArrayList;

public class Cycle {
    private final int nodes;
    private final int edges;
    private Graph<Integer, DefaultEdge> g;
    private ArrayList<Integer> cycle;

    public Cycle(int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph<Integer, DefaultEdge>  createGraph() {
        GraphGenerator<Integer, DefaultEdge, Integer> gen = new GnmRandomGraphGenerator<>(nodes, edges);
        g = new DefaultUndirectedGraph<>(SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultEdgeSupplier(),
                false);
        gen.generateGraph(g);
        return g;
    }

    public Boolean isCyclicUtil(int v, int parent, Boolean[] visited) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (DefaultEdge edge : g.edgesOf(v)) {

            int neighbor = g.getEdgeSource(edge);
            if(neighbor == v)
                neighbor = g.getEdgeTarget(edge);
            if (!visited[neighbor]) {
                cycle.add(neighbor);
                if (isCyclicUtil(neighbor, v, visited)) return true;
            }
            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (neighbor != parent) {
                cycle.add(neighbor);
                return true;
            }
        }
        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    public Boolean hasCycle() {
        int vertices = g.vertexSet().size();

        // Mark all the vertices as not visited and not part of recursion stack
        Boolean[] visited = new Boolean[vertices + 1];
        for (int i = 0; i < vertices; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to detect cycle in different DFS trees
        for (int node : g.vertexSet()) {
            // Don't recur for u if already visited
            cycle = new ArrayList<>();
            if (!visited[node]) {
                cycle.add(node);
                if (isCyclicUtil(node, -1, visited)) return true;
            }
        }
        return false;
    }

    public void printCycle() {
        for(int v: cycle) {
            System.out.print(v + "~");
        }
    }

}

class CycleClient {
    public static void main(String[] args) {
        int[] testVertices = new int[]{10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};
        int[] testEdges = new int[]{20, 70, 150, 170, 700, 850, 1400, 3000, 5800, 7000, 15000, 15000, 27000, 58000};
        Cycle[] testCase = new Cycle[testVertices.length];
        for(int i=0; i< testVertices.length; i++){
            testCase[i] = new Cycle(testVertices[i], testEdges[i]);
            testCase[i].createGraph();
            long start = System.nanoTime();
            boolean result = testCase[i].hasCycle();
            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            if(result) {
                System.out.println("\n" + timeElapsed + " nanoseconds elapsed in finding a cycle for " +
                        testVertices[i] + " vertices" + " and " + testEdges[i] + " edges");
                //Print the knot
                testCase[i].printCycle();
            }
        }

    }


}
