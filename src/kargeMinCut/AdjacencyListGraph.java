package kargeMinCut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class AdjacencyListGraph{
    private boolean DEBUG = false; 
    
    private Map<Vertex, LinkedList<Vertex>> graph = new HashMap<Vertex, LinkedList<Vertex>>(); 
    private Random random = new Random(); 
    
    public AdjacencyListGraph(){}
    
    public AdjacencyListGraph(String filename){ // build from file
        BufferedReader br; 
        try{
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); 
            
            while(line != null){
                if(line != null && !line.isEmpty()){
                    String[] str = line.split("\t");
                    LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
                    
                    for(int index = 1; index < str.length; index++){
                        neighbors.add(new Vertex(str[index]));
                    }
                    graph.put(new Vertex(str[0]), neighbors);
                }
                line = br.readLine();
            }
            
            br.close();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public int getMinCut() throws NoSuchVertexException{
        while(getVerticesCount() > 2){
            if(DEBUG){
                for(Vertex v : getVertices()){
                    System.out.println(v.getLabel() + ": " + getAdjacentVertex(v));
                }
            }
            
            Vertex[] edge = getRandomEdge(); 
            Vertex superNode = contractEdge(edge[0], edge[1]);
            removeSelfLoop(superNode);
            
            if(DEBUG){
                for(Vertex v : getVertices()){
                    System.out.println(v.getLabel() + ": " + getAdjacentVertex(v));
                }
                System.out.println("====================================");
            }
        }
        return getAdjacentVertex(getRandomVertex()).size(); 
    }
    
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
    
    public int removeEdges(Vertex a, Vertex b) throws NoSuchVertexException{
        if(!graph.containsKey(a) || !graph.containsKey(b)){
            throw new NoSuchVertexException();
        }
        List<Vertex> removeFromA = new ArrayList<Vertex>(); 
        List<Vertex> removeFromB = new ArrayList<Vertex>(); 
        
        for(Vertex v : getAdjacentVertex(a)){
            if(v.equals(b)){
                removeFromA.add(v);
            }
        }
        
        for(Vertex v : getAdjacentVertex(b)){
            if(v.equals(a)){
                removeFromB.add(v);
            }
        }
        
        graph.get(a).removeAll(removeFromA);
        graph.get(b).removeAll(removeFromB);
        
        return removeFromA.size(); 
    }
    
    public Vertex[] getRandomEdge() throws NoSuchVertexException{
        Vertex[] result = new Vertex[2];
        
        Vertex start = getRandomVertex(); 
        List<Vertex> neighbors = getAdjacentVertex(start);
        
        while(neighbors.size() < 1){
            start = getRandomVertex(); 
            neighbors = getAdjacentVertex(start);
        }
        
        Vertex end = neighbors.get(random.nextInt(neighbors.size()));
        
        result[0] = start;
        result[1] = end;
        
        return result;
    }
    
    public Vertex contractEdge(Vertex v0, Vertex v1) throws NoSuchVertexException{
        if(DEBUG){
            System.out.println("fuse " + v1 + " into " + v0);
        }
        removeEdges(v0, v1);
        
        List<Vertex> neighbors = getAdjacentVertex(v1);
        while(getAdjacentVertex(v1).size() > 0){ // still have neighbors to relocate
            if(DEBUG){
                System.out.println("neighbors of v1: " + neighbors); 
            }
            Vertex v = neighbors.get(0); // get the first neighbor
            relinkEdge(v, v1, v0);
        }
        
        graph.remove(v1);
        removeSelfLoop(v0);
        return v0; 
    }
    
    /* remove edge between start and oldEnd, make new link between start and newEnd */
    public void relinkEdge(Vertex start, Vertex oldEnd, Vertex newEnd) throws NoSuchVertexException{
        if(DEBUG){
            System.out.println("old:  " + start + " -> " + oldEnd + " new: " + start + " -> " + newEnd);
        }
        if(!graph.containsKey(start) || !graph.containsKey(oldEnd) || !graph.containsKey(newEnd)){
            throw new NoSuchVertexException(); 
        }
        
        int numEdges = removeEdges(start, oldEnd);
        int numAdded = 0; 
        
        while(numAdded < numEdges){
            addEdge(start, newEnd);
            numAdded++; 
        }
    }
    
    public void removeSelfLoop(Vertex v) throws NoSuchVertexException{
        List<Vertex> sameVertex = new ArrayList<Vertex>();
        List<Vertex> neighbors = getAdjacentVertex(v);
        
        for(Vertex vertex : neighbors){
            if(vertex.equals(v))
                sameVertex.add(vertex);
        }
        
        neighbors.removeAll(sameVertex);
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
    
    public Vertex getRandomVertex(){
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