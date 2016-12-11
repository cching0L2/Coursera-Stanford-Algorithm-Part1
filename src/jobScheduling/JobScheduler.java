package jobScheduling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobScheduler {
    private static List<Job> allJobs = new ArrayList<Job>(); 
    
    public static void sortJobs(){
        Collections.sort(allJobs, new JobComparator());
    }
    
    public static long sumCompletionTime(){
        long timeSum = 0; 
        long currSum = 0; 
        
        for(Job j : allJobs){
            timeSum += j.getLength(); 
            currSum += timeSum * j.getWeight(); 
        }
        
        return currSum; 
    }
    
    private static class JobComparator implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            double score1 = o1.getWeight() - o1.getLength(); 
            double score2 = o2.getWeight() - o2.getLength(); 
//            double score1 = (double)o1.getWeight()/o1.getLength(); 
//            double score2 = (double)o2.getWeight()/o2.getLength(); 
            
            if(score1 == score2) return - (o1.getWeight() - o2.getWeight()); 
            else if(score1 > score2) return -1; 
            else return 1; 
            
        }
        
    }
    
    
    public static void main(String[] args){
        String input = "input/part2pa1.txt"; 
        int numJobs; 
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            numJobs = Integer.valueOf(br.readLine());
            
            for(int i = 0; i < numJobs; i++){
                String[] val = br.readLine().split(" "); 
                allJobs.add(new Job(Integer.valueOf(val[0]), Integer.valueOf(val[1])));
            }
            
            JobScheduler.sortJobs();
            
//            for(Job j : allJobs){
//                System.out.println(j);
//            }
            
            System.out.println("weighted completion time: " + JobScheduler.sumCompletionTime());
            
            br.close(); 
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}