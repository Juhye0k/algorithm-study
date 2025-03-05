import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    int distance;
    public Node(int x, int y, int distance){
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}
public class Main {
    static int ar[][];
    static int n,m;
    static boolean visited[][];
    static int result[][];
    static Queue<Node> q;
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 세로의 크기 n
        n=Integer.parseInt(st.nextToken());
        // 가로의 크기 m
        m=Integer.parseInt(st.nextToken());
        visited=new boolean[n][m];
        ar=new int[n][m];
        result=new int[n][m];
        q=new LinkedList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                ar[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        // for문을 통해서 해당 값이 2이면, 해당 정점에서부터 BFS를 돌린다
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(ar[i][j]==2){
                    bfs(new Node(i,j,0));
                }
            }
        }
        // 끝나고 나면 아직 방문하지 않았지만 1인 곳이 있는데, 해당 부분은 -1로 저장해두기
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(ar[i][j]==1 &&!visited[i][j]){
                    result[i][j]=-1;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                bw.write(result[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    public static void bfs(Node node){
        // 시작 정점 방문 체크
        visited[node.x][node.y]=true;
        // 결과 배열에 해당 값 0으로 저장
        result[node.x][node.y]=0;
        // 큐에 삽입
        q.add(node);
        // 큐가 빌때까지 while문 돌리기
        while(!q.isEmpty()){
            Node temp=q.poll();
            for(int i=0;i<4;i++){
                int x=temp.x+dx[i];
                int y=temp.y+dy[i];
                if(x>=0 && x<n && y>=0 && y<m && !visited[x][y]&& ar[x][y]!=0) {
                    visited[x][y] = true;
                    result[x][y] = temp.distance + 1;
                    q.add(new Node(x, y, result[x][y]));
                }
            }
        }
        // 큐에서 값 빼기
        // 상하좌우 탐색
        // 만약 해당 값이 1이고 좌표값을 벗어나지 않을때만 이동
        // 이동하면 큐에 삽입하고, 결과 배열에 이동 길이 저장
    }



}
