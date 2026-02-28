import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        int answer = 0;
        while(!pq.isEmpty()&&pq.peek()<K) {
            if(pq.size()==1 && pq.peek()<K) return -1;
            int first = pq.poll();
            int second = pq.poll();
            int newK = first+2*second;
            pq.add(newK);
            answer++;
        }
        return answer;
    }
}