package graphAlgorithms.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph{
    private List<Integer>[] graph; 
    private int numVertex; 
    
    @SuppressWarnings("unchecked")
    public DirectedGraph(int numVertex){
        this.numVertex = numVertex; 
        graph = (List<Integer>[]) new List[numVertex + 1];
        
        for(int i = 0; i < numVertex + 1; i++){ // graph index starts with 1
            graph[i] = new ArrayList<Integer>(); 
        }
    }
    
    public void reverseAllEdges(){
        @SuppressWarnings("unchecked")
        List<Integer>[] reversedGraph = (List<Integer>[])new List[numVertex + 1];
        
        System.out.println("Reversing edges...");
        
        for(int i = 0; i < numVertex + 1; i++){
            reversedGraph[i] = new ArrayList<Integer>(); 
        }
        
        for(int i = 1; i <= numVertex; i++){
            List<Integer> children = graph[i];
            for(Integer child : children){
                reversedGraph[child].add(i);
            }
        }
        
        graph = reversedGraph; 
        System.out.println("Reverse edges completed");
    }
    
    // v1 -> v2
    public void addEdge(int v1, int v2){
        graph[v1].add(v2); 
    }
    
    public List<Integer> getAllChildren(int v){
        return graph[v];
    }
    
}