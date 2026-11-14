import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b) -> a-b);
        
        Arrays.sort(book_time, (a,b) -> a[0].compareTo(b[0]));
        
        for(int i=0; i< book_time.length; i++) {
            int startTime = calculate(book_time[i][0]);
            int endTime = calculate(book_time[i][1]);
            if(!q.isEmpty() && q.peek()<=startTime) {
                q.remove();
            }
            q.add(endTime+10);
        }
        return q.size();
    }
    public static int calculate(String time) {
        String str[] = time.split(":");
        int hour = Integer.parseInt(str[0]) * 60;
        int minute = Integer.parseInt(str[1]);
        
        return hour + minute;
    }
}