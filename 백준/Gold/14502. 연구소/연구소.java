import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int x, y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {


    static int[][] graph;
    static int N,M;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int answer =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        // 지도의 정보 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 벽 3개를 브루트포스를 이용하여 세운다
        dfs(graph,0);
        System.out.println(answer);
    }
    public static void dfs(int[][] ar, int depth) {
        // 벽 3개를 선택함
        if(depth == 3) {
            // 배열 복사 시키기
            int result = 0;
            int [][] arr = new int[N][M];
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    arr[i][j] = ar[i][j];
                }
            }
            // 지도 전체 돌면서 0의 너비가 몇 개인지 카운트
            for(int i=0;i<ar.length;i++) {
                for(int j=0;j<ar[i].length;j++) {
                    if(ar[i][j]==2) {
                        bfs(new Node(i,j), arr);
                    }
                }
            }
            // 해당 경우에서 최댓값을 추출
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(arr[i][j]==0) result++;
                }
            }
            answer = Math.max(answer, result);
            return;
        }
        for(int i=0;i<ar.length;i++) {
            for(int j=0;j<ar[i].length;j++) {
                // 백트래킹으로 벽 3개 선택
                if(ar[i][j]==0) {
                    ar[i][j] = 1;
                    dfs(ar, depth+1);
                    ar[i][j] = 0;
                }
            }
        }
    }
    public static void bfs(Node node, int[][] ar) {

        // 바이러스 배열을 위해 복사본 ar 만들기

        int count = 0;
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(node);
        visited[node.x][node.y] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0;i<4;i++) {
                int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x>=0 && x<N && y>=0 && y<M && ar[x][y]!=1 && !visited[x][y]) {
                   if(ar[x][y]==0) {
                       ar[x][y]=2;
                   }
                   q.add(new Node(x,y));
                   visited[x][y] = true;
                }
            }
        }
    }
}