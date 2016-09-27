package kargeMinCut;

import java.util.List;
import java.util.Set;

public class Algorithms {
    //private String inputFile = "input/pa3.txt";
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(); 
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        
        try {
            graph.addEdge(a, b);
            graph.addEdge(a, c);
            graph.addEdge(b, c);
            graph.addEdge(d, e);
            graph.addEdge(a, e);
            graph.addEdge(b, c);
            graph.addEdge(e, a);
        } catch(Exception exception){
            exception.printStackTrace();
        }
        
        Set<Vertex> vertices = graph.getVertices();
        
        
        for(Vertex v : vertices){
            try {
                System.out.println(v.getLabel() + ": " + graph.getAdjacentVertex(v));
            } catch(Exception exception){
                exception.printStackTrace();
            }
        }
        
        Vertex[] randEdge = graph.getRandomEdge();
        
        System.out.println("random edge vertex: " + randEdge[0] + " - " + randEdge[1]);
        
        graph.contractEdge(randEdge[0], randEdge[1]);
        
        System.out.println("");
        
        for(Vertex v : vertices){
            try {
                System.out.println(v.getLabel() + ": " + graph.getAdjacentVertex(v));
            } catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }
}