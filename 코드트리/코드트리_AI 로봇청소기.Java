import java.util.*;
import java.io.*;

class Node{
    int x,y;
    Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
public class Main {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] ar;
    static int N;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 격자의 크기
        N = Integer.parseInt(st.nextToken());
        ar = new int[N+1][N+1];
        // 로봇 청소기의 새구
        int K = Integer.parseInt(st.nextToken());
        // 테스트 횟수 L
        int L = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<Node> robots = new LinkedList<>();

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            robots.add(new Node(rx,ry));
        }

        for(int turn=0; turn<L; turn++) {
            // 1. 청소기 이동 + 청소
            for(int j=0; j< robots.size(); j++) {
                // 행 번호가 작은 격자, 행 번호가 같을 경우 열 번호가 작은 격자
                Node robot = robots.get(j);
                Node next = bfs(robots.get(j), robots);
                robot.x = next.x;
                robot.y = next.y;

            }
            for(int j=0; j<robots.size(); j++) {
                Node robot = robots.get(j);
                int bestDir = chooseDir(robot);
                if(bestDir != -1) {
                    clean(robot.x, robot.y, bestDir);
                }
            }
            // 3. 먼지 축척
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(ar[i][j]>0)
                        ar[i][j] += 5;
                }
            }
            // 4. 먼지 확산
            int[][] temp = new int[N+1][N+1];
            for(int i=1; i<=N; i++)
                for(int j=1; j<=N; j++)
                    temp[i][j] = ar[i][j];
            for(int j=1; j<=N; j++) {
                for(int k=1; k<=N; k++) {


                    if(ar[j][k]==0) {
                        int sum = 0;
                        for(int d=0; d<4; d++) {
                            int nx = j +dx[d];
                            int ny = k+ dy[d];
                            if(inRange(nx,ny) && ar[nx][ny]>0) {
                                int spread = ar[nx][ny];
                                sum+=spread;
                            }
                        }
                        temp[j][k]=sum/10;
                    }
                }
            }
            ar = temp;
            int total = 0;
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(ar[i][j]>0)
                        total+=ar[i][j];
                }
            }
            sb.append(total).append("\n");
        }

        System.out.println(sb);
    }
    static Node bfs (Node start, List<Node> robots) {
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start.x,start.y,0});
        visited[start.x][start.y] = true;
        List<int[]> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int dist = cur[2];
            if(dist>minDist) break;
            if(ar[cx][cy]>0) {
                candidates.add(new int[]{cx,cy});

                minDist = dist;
                continue;
            }

            for(int d = 0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(!inRange(nx,ny) || visited[nx][ny] || ar[nx][ny]== -1)
                    continue;
                boolean blocked = false;
                for(Node r: robots) {
                    if(r!=start && r.x==nx && r.y == ny) {
                        blocked = true;
                        break;
                    }
                }
                if(blocked) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx,ny,cur[2]+1});
            }
        }
        if(candidates.isEmpty()) return start;
        candidates.sort((a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        });
        return new Node(candidates.get(0)[0], candidates.get(0)[1]);
    }


    static int chooseDir(Node cur) {
        int best = -1;
        int bestDir = -1;
        int[] dorder = {1,2,3,0};

        for(int d:dorder) {
            int sum = calc(cur.x, cur.y, d);
            if(sum>best) {
                best = sum;
                bestDir = d;
            }
        }
        return bestDir;
    }
    static int calc(int x, int y, int dir) {
        int sum = 0;
        // 본인 위치
        sum+= getDust(x,y);
        // 앞쪽
        sum+= getDust(x+dx[dir],y+dy[dir ]);
        // 오른쪽
        int r = (dir+1) % 4;
        sum += getDust(x+dx[r], y+dy[r]);
        //왼쪽
        int l = (dir+3) % 4;
        sum += getDust(x+dx[l], y+dy[l]);
        return sum;
    }
    static void clean(int x, int y, int dir) {
        removeDust(x,y);
        removeDust(x+dx[dir],y+dy[dir]);
        removeDust(x+dx[(dir+1)%4], y+dy[(dir+1)%4]);
        removeDust(x+dx[(dir+3)%4], y+dy[(dir+3)%4]);
    }
    static void removeDust(int x, int y) {
        if(!inRange(x,y) || ar[x][y]==-1) return;
        ar[x][y] -=Math.min(ar[x][y],20);
    }
    static int getDust(int x, int y) {
        if (!inRange(x, y) || ar[x][y] == -1) return 0;
        return Math.min(ar[x][y], 20); // 최대 20까지만 청소 가능
    }
    static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
}