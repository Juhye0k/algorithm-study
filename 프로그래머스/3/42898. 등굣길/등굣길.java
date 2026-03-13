
import java.util.*;


class Solution {
  
    public int solution(int m, int n, int[][] puddles) {
        
        int [][] dp = new int[n+1][m+1];
        dp[1][1] = 1;
        boolean[][] isPuddles = new boolean[n+1][m+1];
        for(int i=0; i<puddles.length; i++) {
            isPuddles[puddles[i][1]][puddles[i][0]] = true;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(i==1 && j==1) continue;
                
                
                if(!isPuddles[i][j])
                    dp[i][j]= (dp[i-1][j] + dp[i][j-1]) % 1000000007;;
            }
        }
        int answer = dp[n][m] % 1000000007;;
        return answer;
    }
    
    
}