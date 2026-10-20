import java.util.*;
class Node{
    int x1,y1;
    int x2,y2;
    int time;
    
    public Node(int x1, int y1, int x2, int y2, int time) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.time = time;
    }
}
class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        answer = bfs(board);
        return answer;
    }
    public static int bfs(int[][] board) {
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][2];
        
        Queue<Node> q = new ArrayDeque<>();
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        add(q,visited, new Node(0,0,0,1,0));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            int x1 = cur.x1;
            int y1 = cur.y1;
            int x2 = cur.x2;
            int y2 = cur.y2;
            
            if ((x1 == n-1 && y1 == n-1 || x2 == n-1 && y2 == n-1)) {
                return cur.time;
            }
            
            for(int d = 0; d<4; d++) {
                int nx1 = x1 +dx[d];
                int ny1 = y1 + dy[d];
                int nx2 = x2+dx[d];
                int ny2 = y2+dy[d];
                
                if(isEmpty(board,nx1,ny1) && isEmpty(board,nx2,ny2)) {
                    add(q,visited, new Node(nx1,ny1,nx2,ny2,cur.time+1));
                }
            }
            if(x1==x2) {
                for(int d:new int[]{-1,1}) {
                    if(isEmpty(board,x1+d,y1)
                      && isEmpty(board,x2+d,y2)) {
                        add(q,visited, new Node(
                        x1,y1,
                        x1+d,y1, cur.time+1));
                          add(q, visited, new Node(
                            x2, y2,
                            x2 + d, y2,
                            cur.time + 1
                        ));
                    }
                    
                }
            }
            else {
                for(int d: new int[]{1,-1}) {
                    if(isEmpty(board,x1,y1+d)
                      &&isEmpty(board,x2,y2+d)) {
                        add(q,visited, new Node(
                        x1,y1,
                        x1,y1+d,
                        cur.time+1));
                        add(q,visited,new Node(
                        x2,y2,
                        x2,y2+d,
                        cur.time+1));
                    } 
                }
            }
        }
        return -1;
    }
    public static boolean isEmpty(int[][] board, int x, int y) {
        int n = board.length;
        
        return x >=0 && x<n
            && y>=0 && y<n
            && board[x][y] == 0;
    }
    private static void add(
        Queue<Node> q,
        boolean[][][] visited,
        Node next
    ) {
        int row = Math.min(next.x1, next.x2);
        int col = Math.min(next.y1, next.y2);
        int dir = next.x1 == next.x2 ? 0:1;
        
        if(visited[row][col][dir]) {
            return;
        }
        visited[row][col][dir] = true;
        q.add(next);
    }
}