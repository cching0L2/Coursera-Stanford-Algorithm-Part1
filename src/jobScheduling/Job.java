package jobScheduling; 

public class Job {
    private int weight; 
    private int length; 
    
    public Job(int weight, int length) {
        this.weight = weight; 
        this.length = length; 
        
    }
    
    public int getWeight(){
        return this.weight; 
    }
    
    public int getLength(){
        return this.length; 
    }
    
    @Override
    public String toString(){
        return "weight: " + weight + " length: " + length; 
    }
    
}