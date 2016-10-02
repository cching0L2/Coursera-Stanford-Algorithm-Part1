package graphAlgorithms.DataStructure; 

public class Vertex {
    private String label; 
    private boolean visited = false; 
    
    public Vertex(String label){
        this.label = label; 
    }
    
    public String getLabel(){
        return label; 
    }
    
    public void markVisited(){
        this.visited = true; 
    }
    
    public boolean isVisited(){
        return visited; 
    }
}