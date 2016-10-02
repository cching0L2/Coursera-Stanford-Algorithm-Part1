package graphAlgorithms.DataStructure;

public class NoSuchVertexException extends Exception{
    private static final long serialVersionUID = -4783159477939701140L;

    public NoSuchVertexException(Vertex v){
        super(v.getLabel() + " is not a vertex in this graph");
    }
}