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
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Vertex)){
            return false; 
        }
        
        Vertex other = (Vertex)o; 
        
        return other.getLabel().equals(this.getLabel()); 
    }
    
    @Override
    public int hashCode(){
        return label.hashCode(); 
    }
}