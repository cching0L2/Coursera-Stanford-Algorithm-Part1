package kCluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KClusterSmall {
    private static KClusterSmall instance; 
    private List<Set<Integer>> allClusters; 
    private List<DistObj> allDist;  
    
    private KClusterSmall(){
        allClusters = new ArrayList<Set<Integer>>();
        allDist = new ArrayList<DistObj>(); 
    }
    
    public static KClusterSmall getInstance(){
        if(instance == null)
            instance = new KClusterSmall(); 
        return instance; 
    }
    
    // return spacing between clusters after only minK number of clusters are left
    public int getKCluster(int minK){
        while(allClusters.size() > minK){
            DistObj closest = getClosestNodePair(); 

            if(closest == null)
                break; 
            
            mergeClusters(getClusterWithNode(closest.getEnd()), getClusterWithNode(closest.getOtherEnd())); 
            
            System.out.println("Number of clusters: " + allClusters.size());
        }
        
        return getClosestNodePair().getDistance(); 
    }
    
    // get the cluster that this node belongs to 
    private Set<Integer> getClusterWithNode(int node){
        Iterator<Set<Integer>> iterator = allClusters.iterator(); 
        
        while(iterator.hasNext()){
            Set<Integer> cluster = iterator.next(); 
            if(cluster.contains(node)){
                return cluster;
            }
                
        }
        
        return null; 
    }
    
    // return the DistObj with smallest distance between two nodes, return null if no more clusters can be merged
    public DistObj getClosestNodePair(){
        if(allDist.isEmpty())
            return null; 
        
        DistObj distObj = allDist.remove(0);
        Set<Integer> firstCluster = getClusterWithNode(distObj.getEnd());
        Set<Integer> secondCluster = getClusterWithNode(distObj.getOtherEnd());
        
        while(firstCluster.equals(secondCluster)){
            if(allDist.isEmpty())
                return null; 
            
            distObj = allDist.remove(0); 
            firstCluster = getClusterWithNode(distObj.getEnd());
            secondCluster = getClusterWithNode(distObj.getOtherEnd());
        }
        
        return distObj;
    }
    
    // merge all nodes from cluster 2 to cluster 1
    private void mergeClusters(Set<Integer> cluster1, Set<Integer> cluster2){
        cluster1.addAll(cluster2);
    }
    
    public void parseFile(String filename){
        try{
            System.out.println("start parsing file...");
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); 
            int numNodes = Integer.valueOf(line); 
            
            for(int i = 1; i <= numNodes; i++){
                Set<Integer> cluster = new HashSet<Integer>(); 
                cluster.add(i); 
                allClusters.add(cluster);
            }
            
            line = br.readLine(); 
            while(line != null){
                String[] input = line.split(" "); 
                
                DistObj distObj = new DistObj(Integer.valueOf(input[0]), Integer.valueOf(input[1]), Integer.valueOf(input[2])); 
                allDist.add(distObj);
                
                line = br.readLine(); 
            }
            
            br.close();
            
            Collections.sort(allDist);
            
            System.out.println("finished parsing file.");
            
        } catch(Exception e){
            e.printStackTrace(); 
        }
    }
    
    private class DistObj implements Comparable<DistObj>{
        private int node1; 
        private int node2; 
        private int distance; 
        
        public DistObj(int node1, int node2, int distance){
            this.node1 = node1; 
            this.node2 = node2; 
            this.distance = distance; 
        }
        
        public int getEnd(){
            return node1; 
        }
        
        public int getOtherEnd(){
            return node2; 
        }
        
        public int getDistance(){
            return distance; 
        }

        @Override
        public int compareTo(DistObj o) {
            return this.getDistance() - o.getDistance();
        }
        
        @Override 
        public String toString(){
            return "\n" + node1 + " - " + node2 + " | distance: " + distance + " | "; 
        }
    }
     
}