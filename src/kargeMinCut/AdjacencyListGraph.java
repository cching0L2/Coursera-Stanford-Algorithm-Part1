package kargeMinCut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class AdjacencyListGraph{
    private Map<Vertex, LinkedList<Vertex>> graph = new HashMap<Vertex, LinkedList<Vertex>>(); 
    private Random random = new Random(); 
    
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
    
    public Vertex[] getRandomEdge(){
        Vertex[] result = new Vertex[2];
        
        try{
            Vertex start = getRandomVertex(); 
            List<Vertex> neighbors = getAdjacentVertex(start);
            
            while(neighbors.size() < 1){
                start = getRandomVertex(); 
            }
            
            Vertex end = neighbors.get(random.nextInt(neighbors.size()));
            
            result[0] = start;
            result[1] = end;
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    public void contractEdge(Vertex v0, Vertex v1){
        try {
            for(Vertex v: getAdjacentVertex(v1)){
                addEdge(v0, v);
            }
            graph.remove(v1);
            removeSelfLoop(v0);
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
        }
    }
    
    private void removeSelfLoop(Vertex v){
        List<Vertex> sameVertex = new ArrayList<Vertex>();
        
        try {
            List<Vertex> neighbors = getAdjacentVertex(v);
            
            for(Vertex vertex : neighbors){
                if(vertex == v)
                    sameVertex.add(vertex);
            }
            
            neighbors.removeAll(sameVertex);
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
        }
    }
    
    public Set<Vertex> getVertices(){
        return graph.keySet(); 
    }
    
    public int getVerticesCount(){
        return this.getVertices().size();
    }
    
    public List<Vertex> getAdjacentVertex(Vertex a) throws NoSuchVertexException{ // testing only, should make private later
        if(!graph.containsKey(a)){
            throw new NoSuchVertexException();
        }
        
        return graph.get(a);
    }
    
    public Vertex getRandomVertex(){ // TODO: make private later
        Vertex[] vertexArr = getVertices().toArray(new Vertex[0]);
        return vertexArr[random.nextInt(vertexArr.length)]; 
    }
    
    private class NoSuchVertexException extends Exception{
        private static final long serialVersionUID = 1L;
        
        public NoSuchVertexException(String msg){
            super(msg);
        }
        
        public NoSuchVertexException(){
            this("This vertex is not in the graph");
        }
    }
    
}