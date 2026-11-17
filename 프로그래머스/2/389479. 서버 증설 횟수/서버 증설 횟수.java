import java.util.*; 
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int serverCount = 0;
        int playerCount = 0;
        int time = 0;
        for(int i=0; i<players.length; i++) {
            
            // 현재 시간대에 늘어나야 할 플레이어 수
            int num = players[i];
            
            // 서버 반납 처리
            while(!pq.isEmpty() && pq.peek() == time) {
                pq.poll();
            }
            
            // 현재 서버의 수
            serverCount = pq.size();
            
            // 만약 현재 증설된 서버 수로 감당이 안되면
            if((serverCount+1) * m <= num) {
                int result = num - (serverCount+1) * m;
                int upCount = 0;
                if(result % m !=0) {
                    // 얼마나 증설할건지
                    upCount += result / m + 1;
                }
                else {
                    upCount += (result / m) +1;
                }
                // 증설 횟수 증가
                answer += upCount;

                for(int j=0; j<upCount; j++) {
                    pq.add(time+k);
                }
            }
            // 시간 증가
            time++;
        }
        return answer;
    }
}