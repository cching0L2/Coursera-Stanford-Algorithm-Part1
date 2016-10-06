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
        boolean[] isVisited = new boolean[numVertex + 1]; 
        
        for(int label = 1; label <= numVertex; label++){
            List<Integer> children = graph[label];
            System.out.println("label: " + label + " children: " + children);
            isVisited[label] = true; 
            
            for(int i = 0; i < children.size(); i++){
                if(isVisited[children.get(i)]){
                    //System.out.println(children.get(i) + " is visited");
                    continue; 
                }
                System.out.println("children before - " + children.get(i) + ": "+ graph[children.get(i)]);
                graph[children.get(i)].add(label);
                System.out.println("label: " + label + " added to children: " + children);
                System.out.println("children after - " + children.get(i) + ": " + graph[children.get(i)]);
                
                System.out.println("parent before removing out vertex- " + label + ": "+ graph[label]);
                graph[label].remove(i);
                i--; 
                System.out.println("parent after removing out vertex- " + label + ": "+ graph[label]);
            }
            
            System.out.println();
        }
    }
    
    // v1 -> v2
    public void addEdge(int v1, int v2){
        graph[v1].add(v2); 
    }
    
    public List<Integer> getAllChildren(int v){
        return graph[v];
    }
    
}