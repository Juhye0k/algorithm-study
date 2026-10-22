import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(book_time,((a,b) -> calculate(a[0])-calculate(b[0])));
        for(int i=0; i<book_time.length; i++) {
            int start = calculate(book_time[i][0]);
            int end = calculate(book_time[i][1]);
            if(!pq.isEmpty() && pq.peek()<=start) {
                pq.poll();
            }
            pq.add(end+10);
        }
        return pq.size();
    }
    static int calculate(String s) {
        String[] str = s.split(":");
        int time = Integer.parseInt(str[0])*60;
        int minute = Integer.parseInt(str[1]);
        return time+minute;
    }
}