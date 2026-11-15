class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int size = schedules.length;
        boolean[] check = new boolean[size];
        
        
        for(int i=0; i<7; i++) {
            int now = (startday - 1 + i) % 7 + 1;
            
            if(now==6 || now == 7) continue;
            
            for(int j=0; j<timelogs.length; j++) {
                // j번째 직원이 출근하기로 약속한 시간
              int permission = toMinutes(schedules[j]);
                int real = toMinutes(timelogs[j][i]);
                
                if(permission + 10 < real)  {
                    check[j] = true;
                }
            }
            
        }
        for(int i=0; i<size; i++) {
            if(!check[i]) answer++;
        }
        
        return answer;
    }
    private int toMinutes(int time) {
        return time/100 * 60 + time % 100;
    }
}