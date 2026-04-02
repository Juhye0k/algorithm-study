import java.util.*;
class Node  {
    int x,y, count;
    public Node(int x, int y,int count)  {
        this.x = x;
        this.y = y;
        this.count=count;
    }
}

class Solution {
    static int size;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(String[] board) {
        int answer = 0;
        Node start = null;
        size = board.length;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                if(board[i].charAt(j)=='R')
                    start = new Node(i,j,0);
            }
        }
        answer = bfs(start,board);
        return answer;
    }
    public static int bfs(Node start, String[] board) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[size][board[0].length()];
        visited[start.x][start.y] = true;
        q.add(start);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(board[temp.x].charAt(temp.y)=='G') {
                return temp.count;
            }
            for(int i=0; i<4; i++) {
                int nx = temp.x;
                int ny = temp.y;
                while(true) {
                    int x = nx+dx[i];
                    int y = ny+dy[i];
                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length() || board[x].charAt(y) == 'D') {
                        break;
                    }
                    nx=x;
                    ny=y; 
                }
                    if(!visited[nx][ny]) {
                        q.add(new Node(nx,ny,temp.count+1));
                        visited[nx][ny] = true;
                    }
                
            }
        }
        return -1;
    }
    
}