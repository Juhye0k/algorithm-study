import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;
    int time;
    public Node(int x, int y,int time){
        this.x = x;
        this.y = y;
        this.time=time;
    }
}

public class Main {
    static int ar[][];
    static int result;
    static boolean visited[][];
    static Queue<Node> queue;
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    static int M,N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());  // 상자의 크기를 나타내는 M,N
        M=Integer.parseInt(st.nextToken());   // M: 상자의 가로
        N=Integer.parseInt(st.nextToken());   // N: 상자의 세로
        result=0;   // 날짜를 담을 변수
        ar=new int[N][M];  // 토마토 정보를 담기 위한 배열
        visited=new boolean[N][M]; // 방문 여부를 위한 배열
        queue=new LinkedList<>();
        // 토마토들의 정보가 주어짐, 1은 익은 토마토, 0은 익지 않은 토마토, 정수 -1은 들어있지 않은 칸
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                ar[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        boolean oneDay=true;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(ar[i][j]==1){
                    queue.add(new Node(i,j,0));
                    visited[i][j]=true;
                }
                if(ar[i][j]==0){
                    oneDay=false;
                }
            }
        }
        if(oneDay==true)
            result=0;
        else{
            while(!queue.isEmpty()){
                bfs();
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visited[i][j]&&ar[i][j]==0){
                    result=-1;
                    break;
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        Node temp = queue.poll();
        result = temp.time;
        for (int i = 0; i < 4; i++) {
            int x = temp.x + dx[i];
            int y = temp.y + dy[i];
            if ((x >= 0 && x < N) && (y >= 0 && y < M) && !visited[x][y] && ar[x][y] != -1) {
                queue.add(new Node(x, y, temp.time + 1));
                visited[x][y] = true;
            }
        }
    }
}

