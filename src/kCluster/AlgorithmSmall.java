package kCluster;

public class AlgorithmSmall{
    public static void main(String[] args){
        KClusterSmall clusterAlgo = KClusterSmall.getInstance(); 
        
        String filename = "input/part2pa2q1.txt";
        int minK = 4; 
        
        clusterAlgo.parseFile(filename); 
        int spacing = clusterAlgo.getKCluster(minK);
        
        System.out.println("The max spacing is: " + spacing);
        
    }   
}