
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node{
    int x;
    int y;
    int distance;
    public Node(int x, int y,int distance){
        this.x = x;
        this.y = y;
        this.distance=distance;
    }
}

public class Main {
    static int ar[][];
    static boolean visited[][];
    static Queue<Node> q;
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 두 정수 N,M 이 주어진다.
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        ar=new int[N+1][M+1];
        q=new LinkedList<>();
        visited=new boolean[N+1][M+1];
        // M개의 정수로 미로가 주어짐
        for(int i=0; i<N; i++){
            String str=br.readLine();
            for(int j=0; j<M; j++){
                ar[i+1][j+1]=Integer.parseInt(str.charAt(j)+"");
            }
        }
        bfs(new Node(1,1,1));

    }
    public static void bfs(Node start){
        q.add(start);
        visited[start.x][start.y]=true;
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur.x==N && cur.y==M){
                System.out.println(cur.distance);
            }
            for(int i=0; i<4; i++){
                int x=cur.x+dx[i];
                int y=cur.y+dy[i];
                if(x>=1 && x<=N && y>=1 && y<=M && !visited[x][y]&&ar[x][y]==1){
                    visited[x][y]=true;
                    q.add(new Node(x,y,cur.distance+1));
                }
            }

        }
    }
}

