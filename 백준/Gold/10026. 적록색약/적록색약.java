
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


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
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // N의 수
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        for(int i = 0; i < N; i++){
            String str= br.readLine();
            for(int j = 0; j < N; j++){
                graph[i][j] = str.charAt(j);
            }
        }
        int result1 = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]) {
                    bfs(new Node(i,j),graph[i][j]);
                    result1++;
                }
            }
        }
        int result2 =0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]) {
                    bfs2(new Node(i,j),graph[i][j]);
                    result2++;
                }
            }
        }
        sb.append(result1).append(" ").append(result2);
        System.out.println(sb);

    }
    public static void bfs(Node node, char c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if(x>=0 && x<N && y>=0 && y<N && !visited[x][y]&& graph[x][y]==c) {
                    queue.add(new Node(x,y));
                    visited[x][y] = true;
                }
            }
        }
    }
    public static void bfs2(Node node, char c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if(x>=0 && x<N && y>=0 && y<N && !visited[x][y]&& check(c,graph[x][y])) {
                    queue.add(new Node(x,y));
                    visited[x][y] = true;
                }
            }
        }
    }
    public static boolean check(char c, char d) {
        if(c=='R' || c=='G') {
            if(d=='R' || d=='G') return true;
        }
        else {
            if(d=='B')
                return true;
        }
        return false;
    }
}