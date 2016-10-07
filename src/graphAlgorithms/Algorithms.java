package graphAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import graphAlgorithms.DataStructure.DirectedGraph;

public class Algorithms{
    private static DirectedGraph g; 
    //private static int V_COUNT = 12; 
    private static int V_COUNT = 875714;
    private static int MAX_SSC_COUNT = 5; 
    
    private Algorithms(){};
    
    public static void main (String[] args){
        try {
            makeGraph(); 
            getStronglyConnectedComponent(g);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private static void getStronglyConnectedComponent(DirectedGraph g){
        g.reverseAllEdges();
        int[] finishTime = DFSRound1(g);
        g.reverseAllEdges();
        List<Integer> SSCSize = DFSRound2(g, finishTime);
        
        int[] size = new int[SSCSize.size()];
        int counter = 0; 
        
        for(Integer i : SSCSize){
            size[counter++] = i; 
        }
        
        System.out.println("Sorting all SCC sizes...");
        Arrays.sort(size);
        
        System.out.print(MAX_SSC_COUNT + " largest SSC sizes: ");
        for(int i = 0; i < MAX_SSC_COUNT; i++){
            if(i <= size.length - 1){
                System.out.print(size[size.length - 1 - i] + " ");
            } else {
                System.out.print("0 ");
            }
        }
        
        System.out.println();
        
    }
    
    private static int[] DFSRound1(DirectedGraph g){
        System.out.println("Starting DFS Round #1...");
        //printGraph(g);
        int[] finishTime = new int[V_COUNT + 1]; // finishTime[0] := label of vertex that finished first
        int ranking = 1; 
        boolean[] visited = new boolean[V_COUNT + 1];
        
        
        for(int starting = V_COUNT; starting >= 1; starting--){
            if(!visited[starting]){
                Stack<Integer> s = new Stack<Integer>(); 
                s.push(starting);
                
                while(!s.isEmpty()){
                    int vertex = s.peek(); 
                    visited[vertex] = true; 
                    List<Integer> unvisitedChildren = new ArrayList<Integer>(); 
                    
                    for(int child : g.getAllChildren(vertex)){
                        if(!visited[child])
                            unvisitedChildren.add(child);
                    }
                    
                    if(!unvisitedChildren.isEmpty()){
                        s.push(unvisitedChildren.get(0));
                    } else {
                        finishTime[ranking++] = s.pop(); 
                    }
                }
            }
        }
        
        System.out.println("DFS Round #1 Complete");
//        System.out.print("Finish time: FIRST ");
//        for(int rank : finishTime){
//            System.out.print(rank + " ");
//        }
//        System.out.println(" LAST");
        
        return finishTime; 
    }
    
    private static List<Integer> DFSRound2(DirectedGraph g, int[] finishTime){
        System.out.println("Starting DFS Round #2...");
        
        int start; 
        List<Integer> SSCSize = new ArrayList<Integer>();
        boolean[] visited = new boolean[V_COUNT + 1];
        
        for(int rank = V_COUNT; rank >= 1; rank--){ // start with the vertex that finished last 
            if(!visited[finishTime[rank]]){
                Stack<Integer> s = new Stack<Integer>(); 
                int size = 0; 
                start = finishTime[rank];
                
                s.push(start);
                while(!s.isEmpty()){
                    int vertex = s.pop(); 
                    if(!visited[vertex]){
                        visited[vertex] = true; 
                        size++; 
                        
                        for(int child : g.getAllChildren(vertex)){
                            s.push(child);
                        }
                    }
                }
                
                if(size > 0) {
                    SSCSize.add(size); 
                    //System.out.println("Found SSC of size: " + size);
                }
            }
        }
        
        
        System.out.println("DFS Round #2 Complete");
        
        return SSCSize; 
    }
    
    private static void makeGraph() throws IOException{
        System.out.println("Start making graph...");
        BufferedReader br = new BufferedReader(new FileReader("input/pa4.txt"));
        g = new DirectedGraph(V_COUNT); 
        
        String line = br.readLine(); 
        
        while(line != null){
            if(line.length() == 0){
                line = br.readLine();
                continue; 
            }
            
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