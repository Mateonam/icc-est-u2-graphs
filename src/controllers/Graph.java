package controllers;

import models.Node;
import java.util.*;

public class Graph {

    private Set<Node> nodes;
    
    public Graph() {
        this.nodes = new HashSet<>();
    }

    public Node addNode(int value) {
        Node newNode = new Node(value);
        this.nodes.add(newNode);
        return newNode;
    }

    public void addEdge(Node src, Node dest) {
        if (!nodes.contains(src)) {
            System.out.println("Nodo " + src.value + " no encontrado.");
            return;
        }
        if (!nodes.contains(dest)) {
            System.out.println("estino del nodo " + dest.value + " no encontrado.");
            return;
        }
        src.neighbors.add(dest);
        dest.neighbors.add(src);
    }
    public void addEdgeUni(Node src, Node dest) {
        if (!nodes.contains(src)) {
            System.out.println("Nodo " + src.value + " no encontrado.");
            return;
        }
        if (!nodes.contains(dest)) {
            System.out.println("estino del nodo " + dest.value + " no encontrado.");
            return;
        }
        src.neighbors.add(dest);
    }

    public void printGraph() {
        System.out.println("Lista de grafos adyacentes:");
        for (Node node : nodes) {
            System.out.print("Vertex " + node.value + " -> ");
            if (node.neighbors.isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.print("");
                int i = 0;
                for (Node neighbor : node.neighbors) {
                    System.out.print(neighbor.value);
                    if (i < node.neighbors.size() - 1) {
                        System.out.print(" -> ");
                    }
                    i++;
                }
                System.out.println("");
            }
        }
    }

    public void getDFS(Node startNode) {
        if (!nodes.contains(startNode)) {
            System.out.println("Nodo inciado " + startNode.value + " no encontrado en el grafo.");
            return;
        }
        System.out.println("DFS Traversal iniciado " + startNode.value + ":");
        Set<Node> visited = new HashSet<>();
        getDFSUtil(startNode, visited);
        System.out.println();
    }

    private void getDFSUtil(Node node, Set<Node> visited) {
        visited.add(node);
        System.out.print(node.value + " ");

        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                getDFSUtil(neighbor, visited);
            }
        }
    }

    public void getBFS(Node startNode) {
        if (!nodes.contains(startNode)) {
            System.out.println("Nodo iniciado " + startNode.value + " no encontrado.");
            return;
        }
        System.out.println("BFS Traversal iniciado " + startNode.value + ":");
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.value + " ");

            for (Node neighbor : currentNode.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public int[][] getAdjacencyMatrix() {
        if (nodes.isEmpty()) {
            return new int[0][0];
        }

        Map<Node, Integer> nodeToIndex = new HashMap<>();
        int index = 0;
        for (Node node : nodes) {
            nodeToIndex.put(node, index++);
        }

        int size = nodes.size();
        int[][] adjacencyMatrix = new int[size][size];

        for (Node node : nodes) {
            int srcIndex = nodeToIndex.get(node);
            for (Node neighbor : node.neighbors) {
                int destIndex = nodeToIndex.get(neighbor);
                adjacencyMatrix[srcIndex][destIndex] = 1;
            }
        }
        return adjacencyMatrix;
    }

    public void printAdjacencyMatrix() {
        int[][] matrix = getAdjacencyMatrix();
        if (matrix.length == 0) {
            System.out.println("El grafo está vacío.");
            return;
        }

        System.out.print("   "); 
        List<Node> sortedNodes = new ArrayList<>(nodes);
        sortedNodes.sort(Comparator.comparingInt(n -> n.value));

        for (Node node : sortedNodes) {
            System.out.printf("%4d", node.value);
        }
        System.out.println();

        System.out.print("   ");
        for (int i = 0; i < sortedNodes.size(); i++) {
            System.out.print("----");
        }
        System.out.println();

        Map<Node, Integer> nodeToIndex = new HashMap<>();
        int index = 0;
        for (Node node : sortedNodes) { 
            nodeToIndex.put(node, index++);
        }


        for (Node rowNode : sortedNodes) {
            System.out.printf("%2d |", rowNode.value);
            for (Node colNode : sortedNodes) {
                int srcIndex = nodeToIndex.get(rowNode);
                int destIndex = nodeToIndex.get(colNode);
                System.out.printf("%4d", matrix[srcIndex][destIndex]);
            }
            System.out.println();
        }
    }
}
