import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x,y,value;
    Node(int x, int y,int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}


public class Main {


    static int before[][];
    static int after[][];
    static int N,M;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        after = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(Arrays.deepEquals(before, after)) {
            System.out.println("YES");
            return;
        }

        // 전과 후를 비교해서, 다른 좌표를 찾는다.
        Node node = bigu();
        // 다른 좌표를 찾으면, 이후 배열의 값을 저장해놓는다.
        // 전에꺼 배열을 bfs돌려서 해당 값으로 바꿔놓는다.
        int value = after[node.x][node.y];
        bfs(node, value);
        // 이후 이전배열과 이후배열을 비교해서 같으면 yes, 아니면 no
        if(Arrays.deepEquals(before, after)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
    public static Node bigu() {
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(before[i][j]!=after[i][j])
                    return new Node(i,j,before[i][j]);
        return null;
    }

    public static void bfs(Node node,int value) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int a = node.value;
        before[node.x][node.y] = value;
        queue.add(node);
        visited[node.x][node.y] = true;
        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            for(int i=0;i<4;i++) {
                int nx = temp.x+dx[i];
                int ny = temp.y+dy[i];
                if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && a==before[nx][ny]) {
                    queue.add(new Node(nx,ny,after[nx][ny]));
                    before[nx][ny] = value;
                    visited[nx][ny] = true;
                }
            }
        }


    }

}
