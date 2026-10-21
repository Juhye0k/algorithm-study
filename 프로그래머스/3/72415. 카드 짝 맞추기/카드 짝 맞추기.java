import java.util.*;
class Solution {
    static List<int[]>[] cards;
    static int[][] board;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        this.board = board;
        /*
        구하고자 하는 것 -> 모든 카드를 제거하기 위한 키 조작 횟수의 최솟값
        
       
        2. 카드를 방문할 때 최소 거리로 방문해야 하는데 어떻게 탐색할 것인가?
        - dfs로 방문이 완료가 되었다면, 이제 bfs를 사용하여 최소 거리를 구하기
        */
        cards = new ArrayList[7];
        for(int i=1; i<=6; i++) {
            cards[i] = new ArrayList<>();
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                int num = board[i][j];
                
                if(num!=0) {
                    cards[num].add(new int[]{i,j});
                }
            }
        }
        answer = dfs(r,c);
        return answer;
    }
    /*1. 카드 종류의 개수가 여러 개인데, 현재 위치에서 어떤 카드부터 탐색을 해야하는가?
        - 현재 카드의 가지수가 최대 6, 배열은 4x4이다. 따라서 dfs와 백트래킹을 사용하면 된다.
        - dfs를 사용하여 먼저 방문하고자 하는 카드를 고르고, 백트래킹을 통해 최소로 찾자
    */
    static int dfs(int x, int y) {
        int result = Integer.MAX_VALUE;
        
        for(int i=1; i<=6; i++) {
            if(cards[i].isEmpty()) {
                continue;
            }
            int[] a = cards[i].get(0);
            int[] b = cards[i].get(1);
            
            if(board[a[0]][a[1]]==0) {
                continue;
            }
            
            int costAB = bfs(x,y,a[0],a[1]) + bfs(a[0],a[1],b[0],b[1]) + 2;
            int costBA = bfs(x,y,b[0],b[1]) + bfs(b[0],b[1],a[0],a[1]) + 2;
            
            board[a[0]][a[1]] = 0;
            board[b[0]][b[1]] = 0;
            
            result = Math.min(result, costAB +dfs(b[0],b[1]));
            result = Math.min(result, costBA + dfs(a[0],a[1]));
            
            board[a[0]][a[1]] = i;
            board[b[0]][b[1]] = i;
               
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
    public static int bfs(int startX, int startY, int endX, int endY) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];
        visited[startX][startY] = true;
        q.add(new int[]{startX,startY,0});
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int cost = temp[2];
            if(x==endX && y==endY) {
                return cost;
            }
            for(int d=0; d<4; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];
                if(!isRange(nx,ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny,cost+1});
                }
                
                nx = x;
                ny = y;
                
                while(true) {
                    int nextX = nx+dx[d];
                    int nextY = ny+dy[d];
                    
                    if(isRange(nextX,nextY))
                        break;
                    nx = nextX;
                    ny = nextY;
                    if(board[nx][ny]!=0)
                        break;
                }
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny,cost+1});
                }
            }            
        }
        return -1;
    }
    public static boolean isRange(int x, int y) {
        return x<0 || x>=4 || y<0 || y>=4;
    }
}