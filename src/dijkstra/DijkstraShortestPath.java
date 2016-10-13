package dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraShortestPath{
    private static List<LinkedList<AdjListElement>> graph = new ArrayList<LinkedList<AdjListElement>>(); 
    
    private DijkstraShortestPath(){}
    
    private static class AdjListElement{
        int destNode, weight; 
        
        public AdjListElement(int destNode, int weight){
            this.destNode = destNode; 
            this.weight = weight; 
        }
        
        public String toString(){
            return destNode + "," + weight; 
        }
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
        String filename = "input/pa5.txt"; 
        
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
        printGraph(); 
    }
}