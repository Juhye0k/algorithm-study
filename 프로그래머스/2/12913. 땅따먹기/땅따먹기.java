import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int w = land.length;
        int h = land[0].length;
        int[][] dp = new int[w][h];
        for(int i=0; i<h; i++) {
            dp[0][i] = land[0][i];
        }
        for(int i=1; i<w; i++) {
            for(int j=0; j<h; j++) {
                int max = 0;
                for(int k=0; k<h; k++) {
                    if(j==k) continue;
                    max = Math.max(max,dp[i-1][k]);
                }
                dp[i][j] = land[i][j] + max;
            }
        }
        for(int i=0; i<h; i++) {
            answer = Math.max(answer,dp[w-1][i]);
        }
        
        return answer;
    }
}