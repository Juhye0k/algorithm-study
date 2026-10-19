class Time {
    int start,end;
    
    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        String[] playTime = play_time.split(":");
        
        int size = 0;
        size += calculate(playTime);
        
        int ar[] = new int[size+1];
        
        for(int i=0; i<logs.length; i++) {
            String log = logs[i];
            String[] str = log.split("-");
            int start = calculate(str[0].split(":"));
            int end = calculate(str[1].split(":"));
            ar[start] +=1;
            ar[end] -=1;
        }
        int sum[] = new int[size];
        int currentViewers = 0;
        for(int i=0; i<size; i++) {
            currentViewers+=ar[i];
            sum[i] = currentViewers;
        }
        int window = calculate(adv_time.split(":"));
        int left = 0;
        int right = window;
        int bestStart = 0;

        long currentSum = 0;
        for(int i=0; i<window; i++ ) {
            currentSum += sum[i];
            
           
        }
         long maxSum = currentSum;
            int result = 0;
            for(int start = 1; start<=size-window; start++) {
                currentSum -=sum[start-1];
                currentSum +=sum[start+window-1];
                
                if(currentSum > maxSum) {
                    maxSum = currentSum;
                    bestStart = start;
                }
            }
       return String.format(
            "%02d:%02d:%02d",
            bestStart / 3600,
            (bestStart % 3600) / 60,
            bestStart % 60
        );
    }
    public int calculate(String[] str) {
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        int second = Integer.parseInt(str[2]);
        return hour*3600+minute*60+second;
    }
}