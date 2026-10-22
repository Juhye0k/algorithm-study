class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][land[0].length];
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        for(int i=1; i<land.length; i++) {
            for(int j=0; j<land[0].length; j++) {
                int result = 0;
                for(int k=0; k<4; k++) {
                    if(j==k) continue;
                    result = Math.max(result,dp[i-1][k]);
                }
                dp[i][j] = land[i][j] + result;
            }
        } 
        for(int i=0; i<4; i++) {
            answer = Math.max(answer,dp[land.length-1][i]);
        }

        return answer;
    }
}