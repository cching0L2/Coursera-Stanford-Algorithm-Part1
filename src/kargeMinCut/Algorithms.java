package kargeMinCut;

import java.util.Set;

public class Algorithms {
    //private String inputFile = "input/pa3.txt";
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(); 
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        
        try {
            graph.addEdge(a, b);
            graph.addEdge(a, c);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        Set<Vertex> vertices = graph.getVertices();
        
        
        for(Vertex v : vertices){
            try {
                System.out.println(v.getLabel() + ": " + graph.getAdjacentVertex(v));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}