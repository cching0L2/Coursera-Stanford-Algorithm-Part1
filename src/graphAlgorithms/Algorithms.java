package graphAlgorithms;

import java.util.List;
import java.util.Stack;

import graphAlgorithms.DataStructure.Graph;
import graphAlgorithms.DataStructure.NoSuchVertexException;
import graphAlgorithms.DataStructure.Vertex;

public class Algorithms{
    private static Graph g; 
    
    private Algorithms(){};
    
    public static void main (String[] args){
        makeGraph(); 
        dfs(g, (Vertex)g.getAllVertices().toArray()[0]);
    }
    
    private static void dfs(Graph g, Vertex src){
        Stack<Vertex> stack = new Stack<Vertex>(); 
        stack.push(src);
        
        while(!stack.isEmpty()){
            Vertex v = stack.pop(); 
            
            if(!v.isVisited()){
                v.markVisited();
                System.out.print(v.getLabel() + " ");
                
                for(Vertex vertex : g.getUnvisitedChildVertex(v)){
                    stack.push(vertex);
                }
            }
        }
        
        System.out.println();
    }
    
    private static void makeGraph(){
        g = new Graph(); 
        
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        g.addVertex(e);
        
        try {
            g.addEdge(a, b);
            g.addEdge(a, c);
            g.addEdge(b, c);
            g.addEdge(a, d);
            g.addEdge(b, e);
            g.addEdge(c, e);
            g.addEdge(d, e);
        } catch(NoSuchVertexException ex){
            
        }
    };
    
    private static void printGraph(Graph g) {
        for(Vertex v : g.getAllVertices()){
            try {
                System.out.print(v.getLabel() + ": ");
                
                for(Vertex child : g.getAllChildren(v)){
                    System.out.print(child.getLabel());
                    
                    if(child.isVisited()){
                        System.out.print("+");
                    }
                    
                    System.out.print(" ");
                }
                
                System.out.println(); 
            } catch (Exception e) {
                e.printStackTrace();
                continue; 
            }
            
        }
    }
}