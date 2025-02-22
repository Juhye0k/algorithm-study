import java.io.*;
import java.util.*;

class Node {
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, H;
    static int ans;
    // 방향: 상하좌우
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Node> nodes = new ArrayList<>();
    static int[][] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 마을 크기
        M = Integer.parseInt(st.nextToken());  // 초기 체력
        H = Integer.parseInt(st.nextToken());  // 우유 마시면 증가하는 체력

        int[][] map = new int[N][N];
        Node home = null;

        // 입력 받기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    
                    home = new Node(i, j);
                }
            }
        }

        // nodes[0]는 집
        nodes.add(home);
        // 우유(값이 2) 좌표 저장
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(map[i][j] == 2) {
                    nodes.add(new Node(i, j));
                }
            }
        }

        int nodeCount = nodes.size();
        dist = new int[nodeCount][nodeCount];
        // 각 pair 간 최단거리 계산. 갈 수 없는 경우 -1.
        for (int i = 0; i < nodeCount; i++){
            for (int j = 0; j < nodeCount; j++){
                dist[i][j] = bfs(nodes.get(i), nodes.get(j), map);
            }
        }

        visited = new boolean[nodeCount];
       
        ans = 0;
        dfs(0, M, 0);
        System.out.println(ans);
    }

  
    public static int bfs(Node start, Node end, int[][] map) {
        boolean[][] seen = new boolean[N][N];
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        seen[start.x][start.y] = true;
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                Node cur = q.poll();
                // 도착지이면 level 리턴
                if(cur.x == end.x && cur.y == end.y) return level;
                for (int d = 0; d < 4; d++){
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if(nx >= 0 && nx < N && ny >= 0 && ny < N && !seen[nx][ny]){
                        seen[nx][ny] = true;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
            level++;
        }
        // 도달할 수 없는 경우
        return -1;
    }


    public static void dfs(int cur, int hp, int count) {
        // 집(노드0)으로 돌아갈 수 있으면 최대값 갱신
        if(dist[cur][0] != -1 && hp >= dist[cur][0]) {
            ans = Math.max(ans, count);
        }
        
        for (int i = 1; i < nodes.size(); i++){
            // 아직 방문하지 않은 우유
            if(!visited[i] && dist[cur][i] != -1 && hp >= dist[cur][i]){
                visited[i] = true;
                
                dfs(i, hp - dist[cur][i] + H, count + 1);
                visited[i] = false;
            }
        }
    }
}
