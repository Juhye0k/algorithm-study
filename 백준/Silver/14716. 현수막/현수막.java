
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node {
    int x,y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[] dx = {1,1,0,-1,-1,-1,0,1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int M,N;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M =  Integer.parseInt(st.nextToken());
        N =   Integer.parseInt(st.nextToken());
        visited = new boolean[M][N];
        int[][] ar = new int[M][N];
        for(int i=0; i<M; i++) {
            st =  new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(ar[i][j]==1 && !visited[i][j]) {
                    bfs(new Node(i,j),ar);
                    result++;
                }
            }
        }
        System.out.println(result);
    }
    public static void bfs(Node start,int [][] ar) {
        Queue<Node> q = new LinkedList<>();
        visited[start.x][start.y] = true;
        q.add(new Node(start.x, start.y));
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0; i<8; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];
                if(x>=0 && x<M && y>=0 && y<N && !visited[x][y] && ar[x][y]==1 ) {
                    visited[x][y] = true;
                    q.add(new Node(x, y));
                }
            }
        }

    }
}
