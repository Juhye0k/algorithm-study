class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int end = calculate(video_len);
        int now = calculate(pos);
        for(int i=0; i<commands.length; i++) {
            if(now>= calculate(op_start) && now<= calculate(op_end)) {
                now = calculate(op_end);
            }
            
            String command = commands[i];
            
            if(command.equals("next")) {
                if(now + 10 > end) {
                    now = end;
                }
                else {
                    now += 10;
                }
            }
            else  {
                if(now-10<0) {
                    now = 0;
                }
                else {
                    now -= 10;
                }
            }
            
        }
        if(now>= calculate(op_start) && now<= calculate(op_end)) {
                now = calculate(op_end);
            }
        int hour = now / 60;
        int minute = now % 60;
        
        answer = String.format("%02d:%02d", hour, minute); 
        
        return answer;
    }
    
    public static int calculate(String str) {
        String[] s = str.split(":");
        int hour = Integer.parseInt(s[0]) * 60;
        int minute = Integer.parseInt(s[1]);
        
        return hour + minute;
    }
}