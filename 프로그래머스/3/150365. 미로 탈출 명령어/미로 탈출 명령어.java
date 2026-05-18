import java.util.*;
class Solution {
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,-1,1,0};
    static int[][] ar;
    static char[] s = {'d','l','r','u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        ar = new int[n+1][m+1];
        int minDist = Math.abs(x-r) + Math.abs(y-c);
        if(minDist > k || (k-minDist)%2!=0)
            return "impossible";
        int curX = x;
        int curY = y;
        
        
        for(int step = 0; step<k; step++) {
            for(int i=0; i<4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                
                if(nx <1 || nx > n || ny < 1 || ny>m) {
                    continue;
                }
                int remain = k - step - 1;
                int dist = Math.abs(nx-r) + Math.abs(ny-c);
                
                if(dist <= remain && (remain-dist)%2 ==0) {
                    answer.append(s[i]);
                    curX = nx;
                    curY = ny;
                    break;
                }
            }
        }
        return answer.toString();
    }
}