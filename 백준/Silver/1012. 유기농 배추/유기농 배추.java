import java.io.*;
import java.util.*;


class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    static int M,N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Node> queue;
        // 테스트 케이스의 개수 T
        int ar[][];              // 유기농 배추가 심어진 배열
        boolean visited[][];     // 방문했는지 체크하는 배열
        int T=Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            int result=0;
            StringTokenizer st=new StringTokenizer(br.readLine());
            queue=new LinkedList<>();
            // 배추밭 가로길이 M
            M=Integer.parseInt(st.nextToken());
            // 배추밭 세로길이 N
            N=Integer.parseInt(st.nextToken());
            // 배추가 심어져있는 위치의 개수
            int K=Integer.parseInt(st.nextToken());

            ar=new int[M][N];
            visited=new boolean[M][N];
            for(int i=0;i<K;i++) {
                st=new StringTokenizer(br.readLine());
                int xPos=Integer.parseInt(st.nextToken());
                int yPos=Integer.parseInt(st.nextToken());
                ar[xPos][yPos]=1;
            }
            for(int i=0;i<M;i++){                       // 심어진 배추들을 탐색 ㄲ
                for(int j=0;j<N;j++){
                    if(ar[i][j]==1&&!visited[i][j]){       // 만약 해당 배추가 1이고, 방문하지 않았다면
                        Node node=new Node(i,j);           // bfs 돌리기
                        bfs(queue,node,visited,ar);
                        result++;
                    }
                }
            }
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static void bfs(Queue<Node> queue, Node node,boolean visited[][],int ar[][]){
        visited[node.x][node.y]=true;
        queue.add(node);
        while(!queue.isEmpty()){
            Node temp=queue.poll();
            for(int i=0;i<4;i++){
                int x=temp.x+dx[i];
                int y=temp.y+dy[i];
                if(x>=0 && x<=M-1 && y>=0 && y<=N-1){
                    if(!visited[x][y] && ar[x][y]==1){
                        visited[x][y]=true;
                        queue.add(new Node(x,y));
                    }
                }
            }
        }
    }
}

