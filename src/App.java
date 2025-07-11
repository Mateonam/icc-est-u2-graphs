import models.*;
import controllers.*;
public class App {
    public static void main(String[] args) throws Exception {
        Graph gp = new Graph();
        Node n0 = gp.addNode(0);
        Node n1 = gp.addNode(1);
        Node n2 = gp.addNode(2);
        Node n3 = gp.addNode(3);
        Node n4 = gp.addNode(4);
        Node n5 = gp.addNode(5);
        Node n7 = gp.addNode(7);
        Node n8 = gp.addNode(8);
        Node n9 = gp.addNode(9);
    
        gp.addEdge(n0, n1);
        gp.addEdge(n0, n5);
        gp.addEdge(n1, n2);
        gp.addEdge(n1, n4);
        gp.addEdge(n2, n3);
        gp.addEdge(n3, n0);
        gp.addEdge(n3, n9);
        gp.addEdge(n4, n7);
        gp.addEdge(n4, n8);
        gp.addEdge(n7, n3);

        System.out.println("======== Grafo ========");
        gp.printGraph();
    }
}
