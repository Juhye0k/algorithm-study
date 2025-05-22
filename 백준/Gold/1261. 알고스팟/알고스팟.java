import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    int cost;

    public Node(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Main {
    static int ar[][];
    static PriorityQueue<Node> queue;

    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int M,N; // 배열의 크기
    static int dist[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 열 개수
        N = Integer.parseInt(st.nextToken()); // 행 개수
        ar = new int [N][M]; // 배열 맵
        dist = new int[N][M]; // 거리


        for(int i=0; i<N; i++){            // 맵 정보 읽기
            String line = br.readLine();
            for(int j=0; j<M; j++){
                ar[i][j] = line.charAt(j) - '0';
            }
        }
        for(int i=0; i<N; i++){            // 거리 배열 초기화
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int answer = dijkstra();
        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();

    }
    static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        dist[0][0] = 0;
        pq.offer(new Node(0,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (cur.cost > dist[cur.x][cur.y]) continue;

            if (cur.x == N-1 && cur.y == M-1) return cur.cost;

            for(int k = 0; k < 4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;


                int cost = cur.cost + ar[nx][ny];
                if(cost < dist[nx][ny]){
                    dist[nx][ny] = cost;
                    pq.offer(new Node(nx,ny,cost));
                }
            }
        }
        return -1;
    }
}