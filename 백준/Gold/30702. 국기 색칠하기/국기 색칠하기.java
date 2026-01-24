import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x,y;
    char color;
    public Node(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

public class Main {

    static char[][] A;
    static char[][] B;
    static boolean[][] visited;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int N,M;
    static boolean isAnswer = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        B = new char[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                A[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                B[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j]) {
                    bfs(new Node(i,j,A[i][j]));
                }
            }
        }
        // 직접 바꾸기?
        if(isAnswer) System.out.println("YES");
        else System.out.println("NO");
    }
    public static void bfs(Node start) {
        visited[start.x][start.y] = true;
        char bColor = B[start.x][start.y];
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0; i<4; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];
                if(x>=0 && x<N && y>=0 && y<M && !visited[x][y] && A[x][y] == start.color) {
                    if(bColor != B[x][y]){
                        isAnswer = false;
                        return;
                    }
                    visited[x][y] = true;
                    q.offer(new Node(x,y,B[x][y]));
                }
            }
        }
    }
}