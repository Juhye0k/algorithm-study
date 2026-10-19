import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        
        // 모든 문제를 풀 수 있을 때 -> 최단시간 return, 그전까지는 시간 더하기
        
        /*
        일정 이상의 알고력, 코딩력 필요
        공부 -> 1의 시간
        문제를 하나 풀면 알고력과 코딩력이 늘고 일정 시간이 정해져있음
        모든 문제를 다 안풀어도 됨, 여러 번 풀어도 됨
        */
        int targetAlp = 0;
        int targetCop = 0;
        for(int i=0; i<problems.length; i++) {
            targetAlp = Math.max(targetAlp,problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
        }
        int[][] dp = new int[targetAlp+1][targetCop+1];
        alp = Math.min(alp, targetAlp);
        cop = Math.min(cop, targetCop);
        
        for(int[] row : dp) {
            Arrays.fill(row, 1_000_000);
        }
        dp[alp][cop] = 0;
        for(int a = alp; a<= targetAlp; a++) {
            for(int c = cop; c<= targetCop; c++) {
                
                if(a<targetAlp) {
                    dp[a+1][c] = Math.min(
                    dp[a+1][c], dp[a][c] +1);
                }
                if(c<targetCop) {
                    dp[a][c+1] = Math.min(
                        dp[a][c+1],
                        dp[a][c]+1
                    );
                }
                
                for(int[] p : problems) {
                    if(a<p[0] || c < p[1]) {
                        continue;
                    }
                     int nextAlp = Math.min(targetAlp, a + p[2]);
                    int nextCop = Math.min(targetCop, c + p[3]);

                    dp[nextAlp][nextCop] = Math.min(
                        dp[nextAlp][nextCop],
                        dp[a][c] + p[4]
                    );
                }
            }
        }
        
        
        
        return dp[targetAlp][targetCop];
    }
}