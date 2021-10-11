# COT5405-AOA-Graphs
Graph Algorithms with proof.

Prerequisites:

Requires [JGraphT](https://jgrapht.org/) library for random graph generation for running and testing the algorithms.
Java 8.
JGraphT library for graph generation.

Download the jars of JGraphT via maven and add these to lib folder under Project.
Steps to add the library in IntelliJ IDE:
File > Project Structure > Under Project Settings choose Libraries > Add library by clicking + button (from maven) > type org.jgrapht in search bar > download verion: *org.jgrapht:jgrapht-core:1.3.0* > Apply settings.




Part 1

To run the Cycle Find algorithm run CycleClient.main()
The sizes of graphs considered are in increasing order to measure performance and verify time complexity.
Following are the Vertices and Edge sizes considered:
V = {10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};
E = {20, 70, 150, 170, 700, 850, 1400, 3000, 5800, 7000, 15000, 15000, 27000, 58000};
Gi(Vi, Ei) for testcase i.
Every knot is printed and verfified to validate the presence of cycle.
Performance Plots are available as part of the report.

Part 2

To run the Minimum Spanning tree algorithm run MSTClient.main()
The sizes of graphs considered are in increasing order to measure performance and verify time complexity.
Following are the Vertices and Edge sizes considered:
V = {10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};
Ei = Vi + 8 
Gi(Vi, Ei) for testcase i.
The weight of minimum spanning tree generated for Gi using MinimumSpanningTreeDFS Algorithm is validated against the weight of JGraphT's KruskalMinimumSpanningTree applied on Gi using assert statement.

Performance Plots are available as part of the report.
