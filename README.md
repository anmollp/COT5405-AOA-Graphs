# COT5405-AOA-Graphs
Graph Algorithms with proof for the following:

> *Cycle Detection*:
Design and implement an algorithm to find a cycle (just one cycle) in an undirected graph.

> *Minimum Spanning Tree*:
Consider undirected graphs that have n nodes and at most n+8 edges. For such graphs, design an efficient algorithm that finds the minimum spanning tree.

Prerequisites:

* [JGraphT](https://jgrapht.org/) library for random graph generation for running and testing the algorithms.
* Java 8


1. Download the jars of JGraphT via maven and add these to lib folder under Project.
2. Steps to add the library in IntelliJ IDE:\
   Navigate to\
   File > Project Structure > Under Project Settings choose Libraries > Add library by clicking :heavy_plus_sign: button (from maven) > type org.jgrapht in :mag: search bar > download verion: **org.jgrapht:jgrapht-core:1.3.0** > Apply settings.

[GNM Random Graph Generator](https://jgrapht.org/javadoc/org.jgrapht.core/org/jgrapht/generate/GnmRandomGraphGenerator.html) for all random graph generation.

## Cycle Detection

* To run the Cycle Find algorithm run CycleClient.main() inside **_algorithms/src/Cycle.java_**
* The size of graphs considered are in increasing order to measure performance and verify time complexity.
* Following are the Vertices and Edge sizes considered:
> V = {10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};\
> E = {20, 70, 150, 170, 700, 850, 1400, 3000, 5800, 7000, 15000, 15000, 27000, 58000};\
> G<sub>i</sub>(V<sub>i</sub>, E<sub>i</sub>) for testcase i.
* Every knot is printed and verfified to validate the presence of cycle.
* Performance Plots are available as part of the [report](https://github.com/anmollp/COT5405-AOA-Graphs/blob/main/report/CycleFinding.pdf).
* Latex source of report available [here](https://github.com/anmollp/COT5405-AOA-Graphs/blob/main/latex/AoA-Assignment1-Part1.tex).

## Minimum spanning tree

* To run the Minimum Spanning tree algorithm run MSTClient.main() inside **_algorithms/src/MST.java_**
* The size of graphs considered are in increasing order to measure performance and verify time complexity.
* Following are the Vertices and Edge sizes considered:
> V = {10, 50, 100, 125, 500, 760, 1000, 2500, 5000, 6000, 10000, 13000, 23000, 50000};\
> E<sub>i</sub> = Vi + 8 \
> Gi(V<sub>i</sub>, E<sub>i</sub>) for testcase i.
* The weight of minimum spanning tree generated for **G<sub>i</sub>** using **MinimumSpanningTreeDFS** Algorithm is validated against the weight of JGraphT's **KruskalMinimumSpanningTree** applied on **G<sub>i</sub>** using assert statement.
* Performance Plots are available as part of the [report](https://github.com/anmollp/COT5405-AOA-Graphs/blob/main/report/MinimumSpanningTree.pdf).
* Latex source of report available [here](https://github.com/anmollp/COT5405-AOA-Graphs/blob/main/latex/AOA-Assignment1-Part2.tex).
