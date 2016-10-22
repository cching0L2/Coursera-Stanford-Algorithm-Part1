package median;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Median{
    final static String filename = "input/pa6-median.txt";
    static long medianSum = 0; 
    static long medianCount = 0; 
    static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // largest half
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // smallest half
    
    private static void addNum(int i){
        //System.out.println("adding: " + i);
        if(maxHeap.isEmpty()){
            maxHeap.add(i); 
            medianSum += getMedian(); 
            medianCount++;
            return; 
        } else if(minHeap.isEmpty()){
            if(i >= maxHeap.peek()){
                minHeap.add(i);
            } else {
                minHeap.add(maxHeap.poll());
                maxHeap.add(i);
            }
            medianSum += getMedian(); 
            medianCount++;
            return; 
        } else if(i > minHeap.peek()){
            minHeap.add(i);
        } else if(i < maxHeap.peek()){
            maxHeap.add(i);
        } else {
            minHeap.add(i);
        }
        
        if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.add(minHeap.poll());
        } else if(maxHeap.size()  - minHeap.size() > 0){
            minHeap.add(maxHeap.poll());
        }
        
        medianSum += getMedian(); 
        medianCount++;
    }
    
    private static int getMedian(){
        // if odd number of inputs, extract from top of min heap 
        // if even number of inputs, extract from top of max heap
        
        if(minHeap.isEmpty()) return maxHeap.peek(); 
        else if(maxHeap.isEmpty()) return minHeap.peek(); 
        if((minHeap.size() + maxHeap.size()) % 2 == 0)
            return maxHeap.peek();
        else 
            return minHeap.peek(); 
    }
    
    @SuppressWarnings("unused")
    private static void printHeap(){
        System.out.println("minHeap: " + minHeap);
        System.out.println("maxHeap: " + maxHeap);
    }
    
    private static void fileParser(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); 
            
            while(line != null){
                addNum(Integer.parseInt(line)); 
                line = br.readLine(); 
            }
            
            System.out.println(medianSum % medianCount);
            br.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        fileParser(); 
    }
}