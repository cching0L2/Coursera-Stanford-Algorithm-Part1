package graphAlgorithms;

import graphAlgorithms.DataStructure.Graph;
import graphAlgorithms.DataStructure.Vertex;

public class Algorithms{
    private static Graph g; 
    
    private Algorithms(){};
    
    public static void main (String[] args){
        makeGraph(); 
        printGraph(g);
    }
    
    public static void makeGraph(){
        g = new Graph(); 
        
        Vertex a = new Vertex("a");
    };
    
    private static void printGraph(Graph g) {
        for(Vertex v : g.getAllVertices()){
            try {
                System.out.print(v.getLabel() + ": ");
                
                for(Vertex child : g.getAllChildren(v)){
                    System.out.print(child.getLabel() + " ");
                }
                
                System.out.println(); 
            } catch (Exception e) {
                e.printStackTrace();
                continue; 
            }
            
        }
    }
}