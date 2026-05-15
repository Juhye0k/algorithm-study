import java.util.*;
class Node implements Comparable<Node>{
    int x,y;
    int cur;
    int cost;
    public Node(int x, int y, int cur,int cost) {
        this.x = x;
        this.y = y;
        this.cur = cur;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return this.cost-o.cost;
    }
}
class Solution {
    static int[][] graph;
    static int size;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(int[][] board) {
        int answer = 0;
        size = board.length;
        graph = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                graph[i][j] = board[i][j];
            }
        }
        answer = bfs();
        
        return answer;
    }
    public static int bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0,0,5,0));
        int answer = Integer.MAX_VALUE;
        int[][][] cost = new int[size][size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }      
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(temp.x==size-1 && temp.y==size-1)  {
               return temp.cost;
            }
            for(int i=0; i<4; i++) {
                int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x<0 || x>=size || y<0 || y>=size) continue;
                if(graph[x][y]==1) continue;
                int nextCost;
                if(temp.cur!=5 && temp.cur!=i) {
                    nextCost = 600;
                }
                else
                    nextCost = 100;
                if (cost[x][y][i] > nextCost) {
                    cost[x][y][i] = nextCost;
                    q.add(new Node(x, y, i, temp.cost+nextCost));
                }
            }
        }
        return -1;
    }
}