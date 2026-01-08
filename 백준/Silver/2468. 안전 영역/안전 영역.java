
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x,y;
    int value;
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {

    static int[][] ar;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int N;
    static boolean[][] drown;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        ar = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1부터 100까지, 잠기는 곳을 탐색
        for(int i=0;i<=100;i++) {
            drown = new boolean[N][N];
            visited = new boolean[N][N];
            for(int j=0;j<N;j++) {
                for(int k=0;k<N;k++) {
                    if(ar[j][k]<=i)
                        drown[j][k] = true;
                }
            }
            // 잠긴 부분을 확인한 후 , bfs 돌려가며 넓이가 몇개인지 count
            int count = 0;
            for(int j=0;j<N;j++) {
                for(int k=0;k<N;k++) {
                    if(!drown[j][k] && !visited[j][k]) {
                        bfs(new Node(j,k,ar[j][k]));
                        count++;
                    }
                }
            }
            result = Math.max(count,result);
        }
        System.out.println(result);


    }
    public static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        visited[node.x][node.y] = true;
        q.add(node);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0; i<4; i++) {
                int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x>=0 && x<N && y>=0 && y<N && !visited[x][y] && !drown[x][y]) {
                    q.add(new Node(x,y,ar[x][y]));
                    visited[x][y] = true;
                }
            }
        }

    }
}
