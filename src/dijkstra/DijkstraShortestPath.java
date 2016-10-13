package dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraShortestPath{
    private static boolean USE_SMALL = false; 
    private static int NO_PATH = 1000000; 
    private static List<LinkedList<AdjListElement>> graph = new ArrayList<LinkedList<AdjListElement>>(); 
    
    private DijkstraShortestPath(){}
    
    private static class AdjListElement{
        public int destNode, weight; 
        
        public AdjListElement(int destNode, int weight){
            this.destNode = destNode; 
            this.weight = weight; 
        }
        
        public String toString(){
            return destNode + "," + weight; 
        }
    }
    
    private static void getShortestPath(){
        int[] shortestFromSrc = new int[graph.size()]; // shortestFromSrc[0] is a dummy
        List<Integer> processed = new ArrayList<Integer>(); 
        List<Integer> unprocessed = new ArrayList<Integer>(); 
        
        for(int i = 1; i < graph.size(); i++){
            unprocessed.add(i);
        }
        
        processed.add(unprocessed.remove(0)); // start from 1
        
        while(!unprocessed.isEmpty()){
            
            int lowestSoFarNode = -1; 
            int lowestSoFarScore = Integer.MAX_VALUE; 
            
            for(Integer v : processed){
                List<AdjListElement> adjacent = graph.get(v);
                for(AdjListElement w : adjacent){
                    if(unprocessed.contains(w.destNode)){
                        int score = shortestFromSrc[v] + w.weight; 
                        if(score < lowestSoFarScore){
                            lowestSoFarScore = score; 
                            lowestSoFarNode = w.destNode; 
                        }
                    }
                }
            }
            
            if(lowestSoFarNode == -1){
                System.out.println("graph not connected");
                
                while(!unprocessed.isEmpty()){
                    int unprocessedNode = unprocessed.remove(0);
                    shortestFromSrc[unprocessedNode] = NO_PATH;
                }
                
                break; 
            }
            
            shortestFromSrc[lowestSoFarNode] = lowestSoFarScore; 
            processed.add(lowestSoFarNode); 
            unprocessed.remove(new Integer(lowestSoFarNode));
            
        }
        
        System.out.println("shortest from src: ");
        for(int i = 1; i < shortestFromSrc.length; i++){
            System.out.println("to "+ i +": " + shortestFromSrc[i]);
        }
        System.out.println();
        
        
    }

    private static void printGraph(){
        int counter = 0; 
        
        for(LinkedList<AdjListElement> l : graph){
            if(counter == 0){
                counter++; 
                continue; 
            }
            
            System.out.print("[" + counter + "] ");
            for(AdjListElement ale : l){
                System.out.print(ale + "  ");
            }
            
            System.out.println();
            counter++; 
        }
    }
    
    private static void makeGraph(){
        String filename = USE_SMALL ? "input/pa5-small.txt" : "input/pa5.txt"; 
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename)); 
            String line = br.readLine(); 
            
            graph.add(new LinkedList<AdjListElement>()); // index of vertices start at 1, put dummy list at position 0
            
            while(line != null){
                String[] arr = line.split("\\s+");
                
                LinkedList<AdjListElement> outVertices = new LinkedList<AdjListElement>();
                graph.add(outVertices);
                
                for(int i = 1; i < arr.length; i++){
                    int dest = Integer.parseInt(arr[i].split(",")[0]); 
                    int weight = Integer.parseInt(arr[i].split(",")[1]);
                    outVertices.add(new AdjListElement(dest, weight)); 
                }
                
                line = br.readLine(); 
            }
            
            br.close(); 
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        makeGraph(); 
        getShortestPath(); 
    }
}