package kCluster;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//@SuppressWarnings("unused")
public class KClusterSmall {
    private static KClusterSmall instance; 
    private Set<Set<Integer>> allClusters; 
    private PriorityQueue<DistObj> allDist;  
    
    private KClusterSmall(){
        allClusters = new HashSet<Set<Integer>>();
        allDist = new PriorityQueue<DistObj>(); 
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
        }
        
        return getClosestNodePair().getDistance(); 
    }
    
    // get the cluster that this node belongs to 
    private Set<Integer> getClusterWithNode(int node){
        for(Set<Integer> cluster : allClusters){
            if(cluster.contains(node))
                return cluster;
        }
        
        return null; 
    }
    
    // return the DistObj with smallest distance between two nodes, return null if no more clusters can be merged
    public DistObj getClosestNodePair(){
        DistObj distObj = allDist.poll();
        
        while(distObj != null && getClusterWithNode(distObj.getEnd()).equals(getClusterWithNode(distObj.getOtherEnd()))){
            distObj = allDist.poll(); 
        }
        
        return distObj;
    }
    
    // merge all nodes from cluster 2 to cluster 1
    private void mergeClusters(Set<Integer> cluster1, Set<Integer> cluster2){
        cluster1.addAll(cluster2);
        allClusters.remove(cluster2);
        cluster2 = null; 
    }
    
    private static void parseFile(String filename){
        
    }
    
    public static void main(String[] args){
        KClusterSmall clusterAlgo = KClusterSmall.getInstance(); 
        
        String filename = "input/part2pa2q1.txt";
        int minK = 4; 
        
        parseFile(filename); 
        System.out.println("The max spacing is: " + clusterAlgo.getKCluster(minK));
    }
    
    private class DistObj implements Comparator<DistObj>{
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
        public int compare(DistObj o1, DistObj o2) {
            return o1.getDistance() - o2.getDistance();
        }
    }
    
    
}