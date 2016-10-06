package graphAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import graphAlgorithms.DataStructure.DirectedGraph;

public class Algorithms{
    private static DirectedGraph g; 
    private static int V_COUNT = 9; 
    
    private Algorithms(){};
    
    public static void main (String[] args){
        try {
            makeGraph(); 
            printGraph(g);
            getStronglyConnectedComponent(g);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private static void getStronglyConnectedComponent(DirectedGraph g){
        DFSRound1(g);
        DFSRound2(g);
    }
    
    private static void DFSRound1(DirectedGraph g){
        //System.out.println("Starting DFS Round #1");
        System.out.println("Reversing edges...");
        g.reverseAllEdges();
        //printGraph(g);
        //System.out.println("Reverse edges completed");
    }
    
    private static void DFSRound2(DirectedGraph g){
        System.out.println("Starting DFS Round #2");
    }
    
    private static void makeGraph() throws IOException{
        System.out.println("Start making graph...");
        BufferedReader br = new BufferedReader(new FileReader("input/pa4-small.txt"));
        g = new DirectedGraph(V_COUNT); 
        
        String line = br.readLine(); 
        
        while(line != null){
            String[] labels = line.split(" ");
            
            g.addEdge(Integer.parseInt(labels[0]), Integer.parseInt(labels[1]));
            
            line = br.readLine(); 
        }
        System.out.println("Done making graph");
        
        br.close();
    };
    
    private static void printGraph(DirectedGraph g) {
        
        for(int i = 1; i <= V_COUNT; i++){
            System.out.print(i + ": ");
            
            for(Integer child : g.getAllChildren(i)){
                System.out.print(child.intValue() + " ");
            }
            
            System.out.println(); 
        }
    }
}