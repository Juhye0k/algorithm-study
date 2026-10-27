class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int now = calculate(pos);
        int end = calculate(video_len);
        
               if(calculate(op_start)<=now && calculate(op_end)>=now) {
                now = calculate(op_end);
            }
        
        for(int i=0; i<commands.length; i++) {
            String command = commands[i];
            
     
            
            if(command.equals("next")) {
                // 남은 시간이 10초 미만일 경우, 영상의 마지막 위치
                if(end-now<10)
                    now = end;
                else now += 10;
            }
            
            else if(command.equals("prev")) {
                if(now<10)
                    now = 0;
                else now-=10;
            }
                   if(calculate(op_start)<=now && calculate(op_end)>=now) {
                now = calculate(op_end);
            }
           
        }
 
      
        return String.format("%02d:%02d", now/60,now%60);
    }
    public static int calculate(String str) {
        String[] s = str.split(":");
        int hour = Integer.parseInt(s[0]) * 60;
        int minute = Integer.parseInt(s[1]);
        return hour+minute;
    }
}