import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int[][] ar;
    static boolean[][] visited;
    static Queue<Node> q;
    static int firstColor;
    static int[] dx ={1,0,-1,0};
    static int[] dy ={0,1,0,-1};
    static int N,M;
    static int X;
    static boolean answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 등굣길 행의 개수 N
        N=Integer.parseInt(br.readLine());
        // 등굣길 열의 개수 M
        M=Integer.parseInt(br.readLine());
        ar=new int[N][M];
        visited=new boolean[N][M];
        q=new LinkedList<>();
        // 보도블록 색깔
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                ar[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        // 지훈이의 점프력을 나타내는 정수 x
        X=Integer.parseInt(br.readLine());
        // 일단 첫 번째 보도블록 색깔 저장해둠
        firstColor=ar[0][0];
        answer=false;
        bfs(new Node(0,0));
        if(answer){
            bw.write("ALIVE");
        }
        else
            bw.write("DEAD");
        bw.flush();
        bw.close();
    }
    public static void bfs(Node node){
        if (ar[0][0] != ar[N-1][M-1]) {
            answer=false;
            return;
        }
        visited[node.x][node.y]=true;
        q.add(node);
        while(!q.isEmpty()){
            Node temp=q.poll();
            if(temp.x==N-1 && temp.y==M-1 && firstColor==ar[temp.x][temp.y]){
                answer=true;
                break;
            }

            for(int dxOff= -X; dxOff <=X; dxOff++){ // 가능한 x 범위
                int maxDy = X-Math.abs(dxOff);      // 남은 점프 여력 계산
                for(int dyOff = -maxDy; dyOff <= maxDy; dyOff++){ //  dyOff 값 설정
                    if (dxOff == 0 && dyOff == 0) continue;
                    int nx = temp.x + dxOff;
                    int ny = temp.y + dyOff;
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (!visited[nx][ny] && ar[nx][ny] == firstColor) {
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }
    }
}