
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static char[][] graph;
    static int[] dx ={1,0,-1,0};
    static int[] dy ={0,1,0,-1};
    static Queue<Node> q;
    static boolean[][] visited;
    static int N,M;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N+1][M+1];
        result = 0;
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
             for(int j=1; j<=M; j++) {
                 graph[i][j] = str.charAt(j-1);
             }
        }
        int startX=0, startY = 0;
        // 도연이 위치 찾기
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(graph[i][j]=='I') {
                    startX = i; startY = j;
                }
            }
        }
        q = new LinkedList<>();
        visited = new boolean[N+1][M+1];
        bfs(new Node(startX,startY));
        if(result == 0)
            System.out.println("TT");
        else
            System.out.println(result);

    }
    public static void bfs(Node node) {
        visited[node.x][node.y] = true;
        q.add(node);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(graph[temp.x][temp.y]=='P')
                result++;

            for(int i=0; i<4; i++) {
                     int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x>=1 && x<=N && y>=1 && y<=M && !visited[x][y] && graph[x][y]!='X') {
                    q.add(new Node(x,y));
                    visited[x][y] = true;
                }
            }
        }
    }

}