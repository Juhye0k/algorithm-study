import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
   
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i=0; i<bridge_length; i++) {
            q.offer(0);
        }
        
        int time = 0;
        int currentWeight = 0;
        int index = 0;
        
        while(index<truck_weights.length || currentWeight >0) {
            time ++;
            
            currentWeight -= q.poll();
            
            if(index < truck_weights.length
              && currentWeight + truck_weights[index] <= weight) {
                int truck = truck_weights[index++];
                q.offer(truck);
                currentWeight += truck;
            } else {
                q.offer(0);
            }
        }
        return time;
    }
}