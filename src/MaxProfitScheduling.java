import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MaxProfitScheduling {

	    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
	    
	        //Given an interval j, it can join any interval whose end< current_start 
	        //So I want all my previous intervals to be lined up nicely
	        //I will sort by end intervals in ascending order
	        // ex: s1---e1   
	        ///     s3---e3
	        ///              s2-- e2 // s2 can join any of the two above.. e1<e3 => sort by end time
	        
	        List<int[]> endTimeAsc= new ArrayList<>();
	        Comparator<int[]> sortedByEndTimeCmp=(a,b) -> Integer.compare(a[1],b[1]);
	        for(int i=0;i<startTime.length;i++){
	            endTimeAsc.add(new int[]{startTime[i],endTime[i],profit[i]});
	        }
	        Collections.sort(endTimeAsc,sortedByEndTimeCmp);
	        java.util.NavigableMap<Integer,Integer> maxUptoMap=new TreeMap<>();
	        
	        for(int[] currentInterval:endTimeAsc){
	            //startTime
	            Integer floorKey=maxUptoMap.floorKey(currentInterval[0]);
	            int maxSoFar=0;
	            if(floorKey!=null){
	                //profit
	               maxSoFar=maxUptoMap.get(floorKey);
	            }
	            int currentProfit=maxSoFar+currentInterval[2];
	            int maxProfitSoFar=maxUptoMap.lastEntry()==null?0:maxUptoMap.lastEntry().getValue();
	            // CUrrent End time stores the max possible profit upto that point
	            //it can come from a previous subset of intervals.(excluding current)
	            maxUptoMap.put(currentInterval[1],Math.max(currentProfit,maxProfitSoFar));
	            
	        }
	        return maxUptoMap.lastEntry().getValue();
	    
	    
	    }
	
}
