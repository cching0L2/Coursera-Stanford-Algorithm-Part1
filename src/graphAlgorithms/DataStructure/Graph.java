package graphAlgorithms.DataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph{
    Map<Vertex, LinkedList<Vertex>> graph = new HashMap<Vertex, LinkedList<Vertex>>(); 
    
    public Graph(){
        
    }
    
    public void addVertex(Vertex v){
        graph.put(v, new LinkedList<Vertex>());
    }
    
    public void addEdge(Vertex v1, Vertex v2) throws NoSuchVertexException{
        if(!graph.containsKey(v1)){
            throw new NoSuchVertexException(v1);
        } else if(!graph.containsKey(v2)){
            throw new NoSuchVertexException(v2);
        }
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
    }
    
    public Vertex getUnvisitedChildVertex(Vertex v){
        try {
            for(Vertex vertex : getAllChildren(v)){
                if(!vertex.isVisited()){
                    return vertex; 
                }
            }
        } catch(NoSuchVertexException nsve){
            return null; 
        }
        
        return null; 
    }
    
    public LinkedList<Vertex> getAllChildren(Vertex v) throws NoSuchVertexException{
        if(!graph.containsKey(v)){
            throw new NoSuchVertexException(v); 
        }
        return graph.get(v); 
    }
    
    public Set<Vertex> getAllVertices(){
        return graph.keySet(); 
    }
    
    public int getVertexCount(){
        return graph.size(); 
    }
    
}