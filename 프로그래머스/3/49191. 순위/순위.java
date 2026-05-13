import java.util.*;
class Solution {
    static boolean[][] visited;
    public int solution(int n, int[][] results) {
        int answer = 0;
        visited = new boolean[n+1][n+1];
        for(int i=0; i<results.length; i++) {
            int n1 = results[i][0];
            int n2 = results[i][1];
            visited[n1][n2] = true;
        }
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(visited[i][k] && visited[k][j]) 
                        visited[i][j] = true;
                }
            }
        }
        for(int i=1; i<=n; i++) {
            int count = 0;
            for(int j=1; j<=n; j++) {
                if(i==j)
                    continue;
                if(visited[i][j] || visited[j][i])
                    count++;
            }
            if(count==n-1)
                answer++;
        }
        return answer;
    }
}