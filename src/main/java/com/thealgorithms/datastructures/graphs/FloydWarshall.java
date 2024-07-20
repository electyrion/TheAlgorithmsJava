package com.thealgorithms.datastructures.graphs;

import java.util.Scanner;

public class FloydWarshall {

    private final int[][] distanceMatrix;
    private final int numberOfVertices; // number of vertices in the graph
    public static final int INFINITY = 999;

    public FloydWarshall(int numberOfVertices) {
        distanceMatrix = new int[numberOfVertices + 1][numberOfVertices + 1]; // stores the value of distance from all the possible path form the source
        // vertex to destination vertex
        // The matrix is initialized with 0's by default
        this.numberOfVertices = numberOfVertices;
    }

    public void floydWarshall(int[][] adjacencyMatrix) { // calculates all the distances from source to destination vertex
        for (int source = 1; source <= numberOfVertices; source++) {
            for (int destination = 1; destination <= numberOfVertices; destination++) {
                distanceMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }
        for (int intermediate = 1; intermediate <= numberOfVertices; intermediate++) {
            for (int source = 1; source <= numberOfVertices; source++) {
                for (int destination = 1; destination <= numberOfVertices; destination++) {
                    if (distanceMatrix[source][intermediate] + distanceMatrix[intermediate][destination] < distanceMatrix[source][destination]) { // calculated distance it get replaced as
                                                                                                                                                  // new shortest distance // if the new
                                                                                                                                                  // distance calculated is less then the
                                                                                                                                                  // earlier shortest
                        distanceMatrix[source][destination] = distanceMatrix[source][intermediate] + distanceMatrix[intermediate][destination];
                    }
                }
            }
        }
        for (int source = 1; source <= numberOfVertices; source++) {
            System.out.print("\t" + source);
        }
        System.out.println();
        for (int source = 1; source <= numberOfVertices; source++) {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberOfVertices; destination++) {
                System.out.print(distanceMatrix[source][destination] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String... arg) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter the number of vertices");
            int numberOfVertices = scan.nextInt();
            int[][] adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
            System.out.println("Enter the Weighted Matrix for the graph");
            for (int source = 1; source <= numberOfVertices; source++) {
                for (int destination = 1; destination <= numberOfVertices; destination++) {
                    adjacencyMatrix[source][destination] = scan.nextInt();
                    if (source == destination) {
                        adjacencyMatrix[source][destination] = 0;
                        continue;
                    }
                    if (adjacencyMatrix[source][destination] == 0) {
                        adjacencyMatrix[source][destination] = INFINITY;
                    }
                }
            }
            System.out.println("The Transitive Closure of the Graph");
            FloydWarshall floydWarshall = new FloydWarshall(numberOfVertices);
            floydWarshall.floydWarshall(adjacencyMatrix);
        }
    }
}
