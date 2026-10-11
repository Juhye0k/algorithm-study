class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        int [][] dp = new int[2][n];
        
        dp[1][0] = money[0];
        dp[0][0] = 0;
        dp[1][1] = dp[1][0];
        dp[0][1] = money[1];
        
        for(int i=2; i<n-1; i++) {
            dp[1][i] = Math.max(dp[1][i-2]+money[i], dp[1][i-1]);
        }
        for(int i=2; i<n; i++) {
            dp[0][i] = Math.max(dp[0][i-2] + money[i], dp[0][i-1]);
        }
        answer = Math.max(dp[1][n-2], dp[0][n-1]);
        return answer;
    }
}