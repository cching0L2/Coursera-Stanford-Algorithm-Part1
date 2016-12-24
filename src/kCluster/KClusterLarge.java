package kCluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class KClusterLarge{
    private static Set<Integer> vertices; 
    private static Map<Integer, Integer> vertexIndex; // give each vertex a 
    private static UnionFind unionFind; 
    
    private static int NUM_VERTICES; 
    private static int NUM_BITS; 
    
    public static void main(String[] args){
        String filename = "input/part2pa2q2.txt";
        long startTime = System.currentTimeMillis(); 
        long endTime; 
        
        vertices = getVerticesFromFile(filename);
        NUM_VERTICES = vertices.size(); 
        
        vertexIndex = new HashMap<Integer, Integer>(); 
        unionFind = new UnionFind(NUM_VERTICES); 
        
        int count = 0; 
        for(Integer i : vertices){
            vertexIndex.put(i, count++); 
        }
        
        int minNumCluster = getMinNumCluster(3);
        
        endTime = System.currentTimeMillis(); 
        
        System.out.println("result: " + minNumCluster + " runtime: " + (endTime - startTime) + " ms");
        
    }
    
    // return minimum number of cluster required to put all vertices with distance strictly less than minDist into same clusters
    public static int getMinNumCluster(int minDist){
        for(int i = 1; i < minDist; i++){
            for(Integer vertex : vertices) {
                Set<Integer> perm = new HashSet<Integer>(); 
                perm.add(vertex); 
                
                getPerm(i, perm);
                
                
                for(Integer target : perm) {
                    boolean contains = vertices.contains(target); 
                    if(vertices.contains(target)) // this vertex exists
                        unionFind.union(vertexIndex.get(target), vertexIndex.get(vertex));
                }
                
            }
        }
        
        return unionFind.getCount(); 
    }
    
    private static void getPerm(int numDiff, Set<Integer> input){
        if(numDiff == 0) return;
            
        getPerm(numDiff - 1, input); 

        Set<Integer> perm = new HashSet<Integer>();
        for(Integer i : input){ // NUM_BITS number of permutations
            for(int pos = 0; pos < NUM_BITS; pos++){
                int result = i ^ (1 << pos); 
                perm.add(result);
                
            }
        }
        
        input.clear();
        input.addAll(perm);
    }
    
    // ======= file operations ======= // 
    
    public static Set<Integer> getVerticesFromFile(String filename){
        Set<Integer> vertices = new HashSet<Integer>(); 
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename)); 
            String line = br.readLine(); 
            
            NUM_BITS = Integer.valueOf(line.split(" ")[1]);
            
            line = br.readLine(); 
            
            while(line != null){
                int num = Integer.parseInt(line.replace(" ", ""), 2); 
                vertices.add(num);
                
                line = br.readLine();
            }
            
            br.close(); 
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return vertices; 
    }
    
}