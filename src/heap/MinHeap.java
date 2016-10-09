package heap;

import java.util.Arrays;

public class MinHeap{
    protected int[] heap; 
    protected int size; // size of the heap, pointer to position where next leave should go 
    
    public MinHeap(){
        heap = new int[1];
        size = 0; 
    }
    
    public void insert(int i){
        System.out.println("inserting " + i + "...");
        heap[size++] = i; 
        
        if(size == heap.length){
            resize(); 
        }
        
        bubbleUp(); 
    }
    
    public int extractMin(){
        assert(!isEmpty());
        
        int min = heap[0];
        
        heap[0] = 0; 
        swap(0, size - 1);
        size--; 
        
        bubbleDown(); 
        
        return min; 
    }
    
    public boolean isEmpty(){
        return heap[0] == 0; 
    }
    
    /* assume the last leaf node, and only the last leaf node has the possibility of violating heap properties */
    private void bubbleUp(){
        assert(!isEmpty());
        
        int lastLeaf = size - 1;  
        int currNode = lastLeaf; // node currently making decision on
        int parent = getParent(lastLeaf);
        
        while(currNode != 0 && heap[parent] > heap[currNode]){ // if we are at the root already, we are done with all swapping
            swap(parent, currNode); 
            currNode = parent; 
            parent = getParent(currNode);
        }
    }
    
    /* assume the root node, and only the root node has the possibility of violating heap properties */
    private void bubbleDown(){
        int currNode = 0; 
        int leftChild = getLeftChild(currNode);
        int rightChild = getRightChild(currNode);

        while((hasLeftChild(currNode) && heap[currNode] > heap[leftChild]) || (hasRightChild(currNode) && heap[currNode] > heap[rightChild])){            
            int smallest; 
            
            if(hasLeftChild(currNode) && hasRightChild(currNode))
                smallest = (heap[leftChild] > heap[rightChild]) ? rightChild : leftChild; 
            else
                smallest = (hasLeftChild(currNode)) ? leftChild : rightChild; 
            
            swap(smallest, currNode);
            
            currNode = smallest; 
            rightChild = getRightChild(currNode);
            leftChild = getLeftChild(currNode);
        }
        
        System.out.println();
        
    }
    
    private void swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp; 
    }
    
    private int getLeftChild(int i){
        return 2*(i+1) - 1;
    }
    
    private int getRightChild(int i){
        return 2*(i+1);
    }
    
    private int getParent(int i){
        if(i % 2 == 0){
            return i/2 - 1; 
        } else {
            return (i-1)/2; 
        }
    }
    
    private boolean hasLeftChild(int i){
        int leftChild = getLeftChild(i);
        
        return leftChild < size && heap[leftChild] != 0; 
    }
    
    private boolean hasRightChild(int i){
        int rightChild = getRightChild(i);
        
        return rightChild < size && heap[rightChild] != 0; 
    }
    
    private void resize(){
        heap = Arrays.copyOf(heap, heap.length * 2);
    }
    
    public void printHeap(){
        int levelStart = 0; 
        int levelSize = 1; 
        
        while(levelSize <= heap.length / 2){
            for(int i = 0; i < levelSize; i++){
                System.out.print(heap[levelStart + i] + " ");
            }
            System.out.println();
            
            levelStart += levelSize; 
            levelSize *= 2; 
        }
    }
    
    public static void main(String[] args){
        int[] arr = {4, 4, 8, 9, 4, 12, 9, 11, 13};
        Arrays.sort(arr);
        MinHeap heap = new MinHeap();
        
        for(int i : arr){
            heap.insert(i);
        }
        
        heap.printHeap(); 
        
        while(!heap.isEmpty()){
            int min = heap.extractMin();
            System.out.println("min: " + min);
            heap.printHeap(); 
        }
    }
}