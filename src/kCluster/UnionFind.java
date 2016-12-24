package kCluster; 

public class UnionFind{
    int[] parent; 
    int count; 
    
    public UnionFind(int size){
        parent = new int[size]; 
        count = size; 
        
        for(int i = 0; i < count; i++){
            parent[i] = i; 
        }
    }
    
    public int getCount(){
        return count; 
    }
    
    public int find(int vertex){
        int root = vertex;
        
        while(root != parent[root]){ // find the actual root 
            root = parent[root]; 
        }
        
        while(vertex != root){
            int upVertex = parent[vertex]; 
            parent[vertex] = root; 
            vertex = upVertex; 
        }
        
        return root; 
    }
    
    public void union(int a, int b){
        int aLeader = find(a); 
        int bLeader = find(b); 
        
        if(aLeader == bLeader)
            return; 
        
        parent[aLeader] = bLeader; 
        
        count--; 
    }
}