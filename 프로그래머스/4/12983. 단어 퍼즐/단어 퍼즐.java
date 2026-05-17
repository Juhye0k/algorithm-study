import java.util.*;
class Solution {
    public int solution(String[] strs, String t) {
        int answer = 0;
        int n = t.length();
        int INF = 987654321;
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for(int i = 0; i < n; i++) {
            if(dp[i] == INF) continue;
            
            for(String str : strs) {
                int len = str.length();
                
                if(i+len<=n && t.substring(i, i+len).equals(str)){
                    dp[i+len] = Math.min(dp[i+len],dp[i]+1);
                }
            }
        }

        return dp[n] == INF?-1:dp[n];
    }
}