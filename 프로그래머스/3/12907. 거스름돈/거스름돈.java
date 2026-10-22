class Solution {
    public int solution(int n, int[] money) {
        int mod = 1_000_000_007;
        
        // 앞에서 i종류의 동전을 사용해서 j원을 만드는 방법 수
        int[][] dp = new int[money.length + 1][n + 1];

        // 아무것도 고르지 않으면 0원을 만드는 방법
        for (int i = 0; i <= money.length; i++) {
            dp[i][0] = 1;
        }
        
        // 
        for (int i = 1; i <= money.length; i++) {
            // 이번에 사용할 동전을 선택
            int coin = money[i - 1];

            for (int j = 1; j <= n; j++) {
                // 이번 동전을 안쓰는 경우
                dp[i][j] = dp[i - 1][j];
                
                // 이번 동전을 한 개 이상 쓰는 경우
                if (j >= coin) {
                    dp[i][j] =
                        (dp[i][j] + dp[i][j - coin]) % mod;
                }
            }
        }

        return dp[money.length][n];
    }
}