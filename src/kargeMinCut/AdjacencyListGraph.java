package kargeMinCut;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListGraph{
    private Map<Vertex, LinkedList<Vertex>> graph = new HashMap<Vertex, LinkedList<Vertex>>(); 
    
    public AdjacencyListGraph(){};
    
    public void addVertex(Vertex a){
        graph.put(a, new LinkedList<Vertex>());
    }
    
    public void addEdge(Vertex a, Vertex b) throws NoSuchVertexException{
        if(!graph.containsKey(a) || !graph.containsKey(b)){
            throw new NoSuchVertexException();
        }
        graph.get(a).add(b);
        graph.get(b).add(a);
    }
    
    public Set<Vertex> getVertices(){
        return graph.keySet(); 
    }
    
    public List<Vertex> getAdjacentVertex(Vertex a) throws NoSuchVertexException{
        if(!graph.containsKey(a)){
            throw new NoSuchVertexException();
        }
        
        return Collections.unmodifiableList(graph.get(a));
    }
    
    class NoSuchVertexException extends Exception{
        private static final long serialVersionUID = 1L;
        
        public NoSuchVertexException(String msg){
            super(msg);
        }
        
        public NoSuchVertexException(){
            this("This vertex is not in the graph");
        }
    }
    
}