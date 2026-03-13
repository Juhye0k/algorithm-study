import java.util.*;
class Node{
    int x,y, count;
    public Node(int x, int y, int count){
        this.x=x;
        this.y=y;
        this.count = count;
    }
}

class Solution {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(int[][] maps) {
        int answer = 0;
        answer = bfs(new Node(0,0,1), maps);
        return answer;
    }
    public static int bfs(Node start, int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;
        q.add(start);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0; i<4; i++) {
                if(temp.x==n-1 && temp.y==m-1)
                    return temp.count;
                int x= temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x>=0 && x<n && y>=0 && y<m && !visited[x][y]&& maps[x][y]==1) {
                    visited[x][y] = true;
                    q.add(new Node(x,y,temp.count+1));
                }
            }
        }
        return -1;
    }
}