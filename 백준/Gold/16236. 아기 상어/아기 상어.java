
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



class Fish {
    int x,y;
    int size;
    public Fish(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }
}

public class Main {


    static int[][] graph;
    static int sharkSize = 2;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] dist;
    static int sharkX, sharkY;
    static boolean[][] visited;
    static int N;
    static int time =0;
    static int fishCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 공간의 크기 N
        N = Integer.parseInt(st.nextToken());
        graph = new int[N+1][N+1];
        // 공간의 상태
        // 0 : 빈칸, 1~6 : 물고키의 크기, 9: 아기 상어 위치
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(graph[i][j]==9){
                    sharkX = i;
                    sharkY = j;
                    graph[i][j] = 0;
                }
            }
        }

        while(true){
            // 상어로부터 물고기들의 거리를 담아둘 배열. 매번 초기화 해야함
            List<Fish> fishList = new ArrayList<>();
            // 거리 탐색
            bfs();
            // 거리를 바탕으로 후보 물고기들 추출
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(graph[i][j]>0 && graph[i][j]<sharkSize&&dist[i][j]!= -1){
                        fishList.add(new Fish(i,j,graph[i][j]));
                    }
                }
            }
            if(fishList.isEmpty()) break;
            // 거리가 가장 가까운 물고기 추출
            Collections.sort(fishList, (a,b) ->
                    {
                        if(dist[a.x][a.y]==dist[b.x][b.y]) {
                            if(a.x==b.x) return a.y-b.y;
                            return a.x-b.x;
                        }
                        return dist[a.x][a.y]-dist[b.x][b.y];
                    }
            );

            Fish nextFish = fishList.get(0);
            // 물고기 먹음 -> 0으로
            graph[nextFish.x][nextFish.y] = 0;
            // 크기가 같다면 상어 사이즈 늘리기
            fishCount++;
            if(fishCount== sharkSize) {
                sharkSize++;
                fishCount = 0;
            }
            // 시간 늘리기
            time+= dist[nextFish.x][nextFish.y];
            sharkX = nextFish.x;
            sharkY = nextFish.y;
        }
        System.out.println(time);
    }
    // 거리를 구하는 함수
    public static void bfs() {
        // 방문 배열
        // 현재 상어위치 방문
        dist = new int[N+1][N+1];
        for(int i = 0; i <= N; i++){
            Arrays.fill(dist[i],-1);
        }
        dist[sharkX][sharkY] = 0;
        Queue<Fish> q = new LinkedList<>();
        q.add(new Fish(sharkX,sharkY,sharkSize));
        while(!q.isEmpty()){
            // 현재 위치
            Fish cur = q.poll();
            // 상하좌우 탐색
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
                if(dist[nx][ny]!=-1) continue;
                if(graph[nx][ny] > sharkSize) continue; 
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                q.add(new Fish(nx,ny,cur.size));
            }
        }
    }
}
