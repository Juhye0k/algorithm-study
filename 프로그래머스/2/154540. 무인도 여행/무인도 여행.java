import java.util.*;
class Solution {
    static boolean[][] visited;
    static int row,col;
    static int[][] list;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public List<Integer> solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        row = maps.length;
        col = maps[0].length();
        visited = new boolean[row][col];
        list = new int[row][col];
        for(int i=0; i<row; i++) {
            String str = maps[i];
            for(int j=0; j<col; j++) {
                String s = str.substring(j,j+1);
                int num = s.equals("X")?0:Integer.parseInt(s);
                list[i][j] = num;
            }
        }
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(!visited[i][j] && list[i][j]!=0) {
                    int result = bfs(i,j);
                    answer.add(result);
                }
            }
        }
        if(answer.isEmpty())  {
            answer.add(-1);
            return answer;
        }
        Collections.sort(answer);
        return answer;
    }
    public static int bfs(int startX, int startY) {
        visited[startX][startY] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX,startY});
        int result = list[startX][startY];
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            for(int d=0; d<4; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];
                if(isRange(nx,ny) && !visited[nx][ny] && list[nx][ny]!=0) {
                    visited[nx][ny] = true;
                    result+=list[nx][ny];
                    q.add(new int[]{nx,ny});
                }
            }
        }
        return result;
    }
    public static boolean isRange(int x, int y) {
        return x>=0 && x<row && y>=0 && y<col;
    }
}