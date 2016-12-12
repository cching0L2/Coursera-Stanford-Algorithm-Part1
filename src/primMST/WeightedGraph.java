package primMST; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class WeightedGraph {
    SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);

    public WeightedGraph(String inFile){
        buildGraph(inFile);
    }
    
    private void buildGraph(String inFile) {
        try{
            BufferedReader bf = new BufferedReader(new FileReader(inFile));
            int numVert = Integer.valueOf(bf.readLine().split(" ")[0]);
            
            for(int i = 1; i <= numVert; i++){
                graph.addVertex(i);
            }
            
            String line = bf.readLine(); 
            
            while(line != null){
                String[] val = line.split(" ");
                DefaultWeightedEdge edge = graph.addEdge(Integer.valueOf(val[0]), Integer.valueOf(val[1]));
                graph.setEdgeWeight(edge, Double.valueOf(val[2]));
                line = bf.readLine(); 
            }
            
            bf.close(); 
            
        } catch(Exception e) {
            
        }
        
    }
    
    public Set<DefaultWeightedEdge> getMinimumSpanningTree(){
        Set<DefaultWeightedEdge> mst = new HashSet<DefaultWeightedEdge>();
        
        Set<Integer> allVertices = graph.vertexSet(); 
        Set<Integer> treeVertices = new HashSet<Integer>(); 
        
        treeVertices.add(allVertices.toArray(new Integer[allVertices.size()])[0]);
        
        long sum = 0; 
        
        while(allVertices.size() != treeVertices.size()){
            DefaultWeightedEdge lowest = getMinCostEdge(treeVertices);
            if(lowest == null)
                System.out.println("Error: graph is not connected...");
            else {
                mst.add(lowest);
                treeVertices.add(graph.getEdgeSource(lowest));
                treeVertices.add(graph.getEdgeTarget(lowest));
            }
            
        }
        
        for(DefaultWeightedEdge edge : mst){
            sum += graph.getEdgeWeight(edge);
        }
        
        System.out.println("MST edge cost sum: " + sum);
        
        return mst; 
    }
    
    private DefaultWeightedEdge getMinCostEdge(Set<Integer> V){
        DefaultWeightedEdge lowest = null; 
        
        for(Integer vertex : V){
            for(DefaultWeightedEdge edge : graph.edgesOf(vertex)){
                if(V.contains(Graphs.getOppositeVertex(graph, edge, vertex))){
                    continue; 
                }
                
                if(lowest == null || graph.getEdgeWeight(lowest) > graph.getEdgeWeight(edge))
                    lowest = edge; 
            }
        }
        
        return lowest; 
    }
    
    public static void main(String[] args){
        WeightedGraph graph = new WeightedGraph("input/part2pa1q2.txt");
        graph.getMinimumSpanningTree(); 
    }
}