class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        int[][] ar = new int[n+1][m+1];
        for(int i=0; i<puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            ar[y][x]=-1;
        }
        dp[1][1] = 1;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if (i == 1 && j == 1) continue;

                if(ar[i][j]==-1) continue;
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])% 1000000007;
            }
        }
        answer = dp[n][m]% 1000000007;
        return answer;
    }
}