package twoSums;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class TwoSums{
    
    static String filename = "input/pa6-2sum.txt";  // expecting 14
    static final int TARGET_MIN = -10000; 
    static final int TARGET_MAX = 10000; 
    static int result = 0; 
    
	static Set<Long> input = new HashSet<Long>(); 
	static Set<Long> match = new HashSet<Long>(); 
	
	private static void printNumTargets(){
	    for(int target = TARGET_MIN; target <= TARGET_MAX; target++){
	        System.out.println(target);
	        for(Long in : input){
	            if(match.contains(in)) {
	                result++; 
	                break; 
	            }
	            else match.add(target - in);
	        }
	        match.clear();
	    }
	    System.out.println("result: " + result); 
	}
	
	private static void fileParser(){
	    try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); 
            
            while(line != null){
                input.add(Long.parseLong(line));
                line = br.readLine(); 
            }
            
            br.close(); 
            
            for(Long i : input){
            //    System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args){
        fileParser(); 
        printNumTargets(); 
    }
}
