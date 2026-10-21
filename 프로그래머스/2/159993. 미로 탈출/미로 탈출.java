import java.util.*;
class Solution {
    static int row,col;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(String[] maps) {
        int answer = -1;
        row = maps.length;
        col = maps[0].length();
        
        int startX = 0, startY = 0;
        int endX = 0, endY=0;
        int lx=0,ly = 0;
        for(int i=0; i<row; i++) {
            String s = maps[i];
            for(int j=0; j<col; j++) {
                String m = s.substring(j,j+1);
                if(m.equals("L")) {
                    lx = i;
                    ly = j;
                }
                else if(m.equals("S")){
                    startX = i;
                    startY = j;
                }
                else if(m.equals("E")) {
                    endX = i;
                    endY = j;
                }
            }
        }
        int r1=bfs(startX, startY, lx,ly,maps);
        int r2=bfs(lx,ly,endX,endY,maps);
        if(r1 == 0|| r2==0) return -1;
        return r1+r2;
    }
    public static int bfs(int startX, int startY, int endX, int endY,String[] maps) {
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX,startY,0});
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            
            int x = temp[0];
            int y = temp[1];
            int count = temp[2];
            if(x==endX && y == endY) {
                return count;
            }
            for(int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(isRange(nx,ny) && !visited[nx][ny]&&!maps[nx].substring(ny,ny+1).equals("X")) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny,count+1});
                }
            }
        }
        return 0;
    }
    public static boolean isRange(int x, int y) {
        return x>=0 && x<row && y>=0 && y<col;
    }
}