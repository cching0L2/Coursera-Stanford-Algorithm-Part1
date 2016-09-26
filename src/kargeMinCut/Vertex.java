package kargeMinCut;

public class Vertex {
    private String label; 
    
    public Vertex(String label){
        this.label = label; 
    };
    
    public String getLabel(){
        return label; 
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false; 
        }
        if(! (obj instanceof Vertex)){
            return false; 
        }
        Vertex otherVertex = (Vertex)obj; 
        
        return otherVertex.getLabel().equals(this.getLabel()); 
    };
    
    @Override
    public int hashCode(){
        return 0; 
    }
    
    @Override 
    public String toString(){
        return this.getLabel(); 
    }
}