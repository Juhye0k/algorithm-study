import java.util.*;

class Solution {
  
    
    public int solution(String numbers) {
        int[] row = {3,0,0,0,1,1,1,2,2,2};
        int[] col = {1,0,1,2,0,1,2,0,1,2};
        
        int[][] cost = new int[10][10];
                int INF = 1_000_000_000;

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(i==j) {
                    cost[i][j] = 1;
                    continue;
                }
                
                int rowDiff = Math.abs(row[i]-row[j]);
                int colDiff = Math.abs(col[i]-col[j]);
                
                int diagonal = Math.min(rowDiff, colDiff);
                int straight = Math.abs(rowDiff - colDiff);
                
                cost[i][j] = 3*diagonal + 2*straight;
            }
        }
        
        int[][] dp = new int[10][10];
        for (int[] line : dp) {
    Arrays.fill(line, INF);
}
        dp[4][6] = 0;

        for(int index = 0; index<numbers.length(); index++) {
            int num = numbers.charAt(index) - '0';
            
            int[][] nextDp = new int[10][10];
            
            for(int[] line : nextDp) {
                Arrays.fill(line, INF);
            }
            
            for(int left=0; left<10; left++) {
                for(int right=0; right<10; right++) {
                    if(dp[left][right]==INF) 
                        continue;
                    
                    if(num!= right) {
                        nextDp[num][right] = Math.min(
                            nextDp[num][right], dp[left][right] + cost[left][num]);
                        
                    }
                    if(num!= left) {
                         nextDp[left][num] = Math.min(
                            nextDp[left][num],
                            dp[left][right] + cost[right][num]
                        );
                    }
                }
            }
            dp = nextDp;
        }
        int answer = INF;
        
         for (int left = 0; left < 10; left++) {
            for (int right = 0; right < 10; right++) {
                answer = Math.min(answer, dp[left][right]);
            }
        }
       
        
        return answer;
    }
   
    
}