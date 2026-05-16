class Solution {
    public int solution(int[][] board) {
        int answer = 0;

        int w = board.length;
        int h = board[0].length;

        int[][] dp = new int[w][h];

        dp[0][0] = board[0][0];
        answer = Math.max(answer, dp[0][0]);

        for (int i = 1; i < h; i++) {
            dp[0][i] = board[0][i];
            answer = Math.max(answer, dp[0][i]);
        }

        for (int i = 1; i < w; i++) {
            dp[i][0] = board[i][0];
            answer = Math.max(answer, dp[i][0]);
        }

        for (int i = 1; i < w; i++) {
            for (int j = 1; j < h; j++) {
                if (board[i][j] == 1) {
                    dp[i][j] = Math.min(
                        dp[i - 1][j - 1],
                        Math.min(dp[i - 1][j], dp[i][j - 1])
                    ) + 1;
                } else {
                    dp[i][j] = 0;
                }

                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer * answer;
    }
}