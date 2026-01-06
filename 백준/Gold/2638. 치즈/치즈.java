
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


    static int[][] ar;
    static int time = 0;
    static int N,M;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ar = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true){
            // 배열 전부 돌려서 치즈 있는지 체크 -> 없으면 break 후 시간  출력
            if(check())
                break;
            // 가장자리에서 bfs 돌려서, 외부 공기를 마킹해두기 ex) -1
            bfs();
            // 전체 반복문 돌려서 2변 이상을 공유하는 치즈 찾아서 외부 공기로 체크
            cheese();

        }
        System.out.println(time);
    }
    public static boolean check() {
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(ar[i][j]==1)
                    return false;
        return true;
    }
    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0));
        boolean visited[][] = new boolean[N][M];
        visited[0][0] = true;
        ar[0][0] = -1;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i=0;i<4;i++){
                int nx = temp.x+dx[i];
                int ny = temp.y+dy[i];
                if(nx>=0 && nx<N && ny>=0 && ny<M && ar[nx][ny]!=1&& !visited[nx][ny]) {
                    ar[nx][ny] = -1;
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }
    }
    public static void cheese() {
        Queue<Node> q = new LinkedList<>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(ar[i][j]==1) {
                    int count = 0;
                    for(int k=0;k<4;k++) {
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(nx>=0 && nx<N && ny>=0 && ny<M && ar[nx][ny]==-1) count++;
                    }
                    if(count>=2) q.add(new Node(i,j));
                }
            }
        }
        while(!q.isEmpty()) {
            Node temp = q.poll();
            ar[temp.x][temp.y] = -1;
        }
        time++;
    }


}
