import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    int z;
    int distance;
    public Node(int x, int y,int z, int distance){
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = distance;
    }
}

public class Main {
    static int ar[][];
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    static Queue<Node> queue;
    static boolean visited[][][];
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());   // 첫째 줄에 N,M이 주어진다.
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        ar=new int[N][M];                  // 좌표 정보를 담을 배열
        visited=new boolean[N][M][2];      // 방문 여부. 벽을 부쉈는지 확인하기 위해 3차원으로 선언. 1이면 벽을 이제 부순 상태라 더 이상 못 부숨. z 값은 계속 유지
        queue=new LinkedList<>();
        // 다음 줄에 N,M개의 숫자로 맵이 주어짐, 1부터 시작인거 주의
        for(int i=0;i<N;i++){                        // 좌표 정보 입력 받음
            String str=br.readLine();
            for(int j=0;j<M;j++){
                int num=Integer.parseInt(String.valueOf(str.charAt(j)));
                ar[i][j]=num;
            }
        }

        // 벽을 한 번 뚫을 수 있는데, 그걸 언제 뚫어야하는지 어떻게 알지?
        int result=bfs(new Node(1,1,0,1));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    public static int bfs(Node node){
        queue.add(node);                           // 1,1 큐에 추가
        visited[node.x-1][node.y-1][0]=true;           // 방문 여부 추가

        while(!queue.isEmpty()){

            Node temp=queue.poll();
            if(temp.x==N && temp.y==M)
                return temp.distance;

            for(int i=0;i<4;i++){                 // 상하좌우 탐색
                int x=temp.x+dx[i];
                int y=temp.y+dy[i];
                if(x>=1 && x<=N && y>=1 && y<=M){  // 범위 조건 맞는지 체크
                    if(ar[x-1][y-1]==1 && temp.z==0 && !visited[x-1][y-1][1]){ // 벽을 부술 수 있는지 체크함
                        visited[x-1][y-1][1]=true;
                        queue.add(new Node(x,y,1,temp.distance+1));
                    }
                    else if(ar[x-1][y-1]==0 && !visited[x-1][y-1][temp.z]){
                        visited[x-1][y-1][temp.z]=true;
                        queue.add(new Node(x,y,temp.z,temp.distance+1));
                    }
                }
            }
        }
        return -1;
    }
}

