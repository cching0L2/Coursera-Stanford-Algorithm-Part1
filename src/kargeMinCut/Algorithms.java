package kargeMinCut;

public class Algorithms {
    private static String inputFile = "input/pa3.txt";
    private static int NUM_REP = 1000; 
    static AdjacencyListGraph graph; 
    
    public static void main(String[] args) {
        int minSoFar; 
        int minAbs = Integer.MAX_VALUE;
        
        try{
            for(int i = 0; i < NUM_REP; i++){
                graph = new AdjacencyListGraph(inputFile); 
                minSoFar = graph.getMinCut(); 
                minAbs = Math.min(minSoFar, minAbs);
            }
            System.out.println(minAbs);
            printGraph(); 
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
    private static void printGraph(){
        for(Vertex v : graph.getVertices()){
            try {
                System.out.println(v.getLabel() + ": " + graph.getAdjacentVertex(v));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
        System.out.println();
    }
}