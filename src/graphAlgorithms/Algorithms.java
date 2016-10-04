package graphAlgorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import graphAlgorithms.DataStructure.Graph;
import graphAlgorithms.DataStructure.NoSuchVertexException;
import graphAlgorithms.DataStructure.Vertex;

public class Algorithms{
    private static Graph g; 
    
    private Algorithms(){};
    
    public static void main (String[] args){
        try {
            makeGraph(); 
            printGraph(g);
            //dfs(g, (Vertex)g.getAllVertices().toArray()[0]);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private static void dfs(Graph g, Vertex src){
        Stack<Vertex> stack = new Stack<Vertex>(); 
        stack.push(src);
        
        while(!stack.isEmpty()){
            Vertex v = stack.pop(); 
            
            if(!v.isVisited()){
                v.markVisited();
                System.out.println(v.getLabel() + " isVisited: " + v.isVisited());
                //System.out.print(v.getLabel() + " -> ");
                
                for(Vertex vertex : g.getUnvisitedChildVertex(v)){
                    stack.push(vertex);
                }
            }
        }
        
        System.out.println();
    }
    
    private static void makeGraph() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input/pa4-small.txt"));
        g = new Graph(); 
        
        String line = br.readLine(); 
        
        while(line != null){
            String[] labels = line.split(" ");
            labels[0] = labels[0].trim();
            labels[1] = labels[1].trim();
            
            Vertex v1 = g.getVertex(labels[0]);
            Vertex v2 = g.getVertex(labels[1]);
            
            if(v1 == null){
                v1 = new Vertex(labels[0]);
                g.addVertex(v1);
            }
            if(v2 == null){
                v2 = new Vertex(labels[1]);
                g.addVertex(v2);
            }
            
            try{
                g.addEdge(v1, v2);
            } catch(Exception e){
                e.printStackTrace();
            }
            
            line = br.readLine(); 
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