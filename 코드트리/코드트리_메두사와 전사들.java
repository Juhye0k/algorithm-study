import java.util.*;
import java.io.*;

class Soldier {
    int x, y;
    boolean isMove;
    Soldier(int x, int y, boolean isMove) {
        this.x = x; this.y = y; this.isMove = isMove;
    }
}

public class Main {
    static int N;
    static int[][] road;
    static int[][] distFromEnd;
    static int[] dx  = {-1,1,0,0};
    static int[] dy  = {0,0,-1,1};
    static int[] dx2 = {0,0,-1,1};
    static int[] dy2 = {-1,1,0,0};
    static List<Soldier> soldiers;
    static boolean[][] medusaView;
    static boolean[][] soldierMap;
    static final int[][][] VISION = {
            {{-1,-1},{-1,0},{-1,1}},
            {{ 1,-1},{ 1,0},{ 1,1}},
            {{-1,-1},{ 0,-1},{ 1,-1}},
            {{-1, 1},{ 0, 1},{ 1, 1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        road = new int[N][N];
        soldiers = new ArrayList<>(); // ✅ ArrayList

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            soldiers.add(new Soldier(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), true
            ));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                road[i][j] = Integer.parseInt(st.nextToken());
        }

        distFromEnd = bfsFromEnd(er, ec);
        medusaView = new boolean[N][N];
        soldierMap = new boolean[N][N];
        updateSoldierMap();
        int nowX = sr, nowY = sc;

        while (true) {
            int soldierDistance = 0, rockSoldier = 0, deadSoldier = 0;

            // ── 1. 메두사 이동 ──────────────────────────
            int minDist = Integer.MAX_VALUE, nextMX = nowX, nextMY = nowY;
            for (int i = 0; i < 4; i++) {
                int nx=nowX+dx[i], ny=nowY+dy[i];
                if (outCheck(nx,ny)||road[nx][ny]==1) continue;
                int d = distFromEnd[nx][ny];
                if (d==-1) continue;
                if (d < minDist) { minDist=d; nextMX=nx; nextMY=ny; }
            }
            nowX = nextMX; nowY = nextMY;

            for (int i = soldiers.size()-1; i >= 0; i--) {
                Soldier s = soldiers.get(i);
                if (s.x==nowX && s.y==nowY) soldiers.remove(i);
            }
            updateSoldierMap();

            if (nowX==er && nowY==ec) {
                sb.append(0);
                System.out.print(sb);
                return;
            }

            // ── 2. 메두사 시선 ──────────────────────────
            int bestDir = -1, bestCount = -1;
            boolean[][] bestVis = null;

            for (int i = 0; i < 4; i++) {
                boolean[][] vis = buildVisionMap(nowX, nowY, i);
                int cnt = countInVis(vis);
                if (cnt > bestCount) {
                    bestCount = cnt; bestDir = i; bestVis = vis;
                }
            }

            for (int r = 0; r < N; r++)
                Arrays.fill(medusaView[r], false);

            if (bestVis != null) {
                for (int r = 0; r < N; r++)
                    for (int c = 0; c < N; c++)
                        medusaView[r][c] = bestVis[r][c];

                for (Soldier s : soldiers) {
                    if (medusaView[s.x][s.y] && s.isMove) {
                        s.isMove = false;
                        rockSoldier++;
                    }
                }
            }

            // ── 3. 전사 이동 ────────────────────────────
            for (Soldier soldier : soldiers) {
                if (!soldier.isMove) continue;

                int moved = 0;
                int curDist = Math.abs(soldier.x-nowX)+Math.abs(soldier.y-nowY);
                for (int i = 0; i < 4; i++) {
                    int nx=soldier.x+dx[i], ny=soldier.y+dy[i];
                    if (outCheck(nx,ny)||medusaView[nx][ny]) continue;
                    if (Math.abs(nx-nowX)+Math.abs(ny-nowY) < curDist) {
                        soldier.x=nx; soldier.y=ny; moved++; break;
                    }
                }
                if (moved>0) soldierDistance++;

                curDist = Math.abs(soldier.x-nowX)+Math.abs(soldier.y-nowY);
                moved = 0;
                for (int i = 0; i < 4; i++) {
                    int nx=soldier.x+dx2[i], ny=soldier.y+dy2[i];
                    if (outCheck(nx,ny)||medusaView[nx][ny]) continue;
                    if (Math.abs(nx-nowX)+Math.abs(ny-nowY) < curDist) {
                        soldier.x=nx; soldier.y=ny; moved++; break;
                    }
                }
                if (moved>0) soldierDistance++;
            }

            // ── 4. 전사 공격 ────────────────────────────
            for (int i = soldiers.size()-1; i >= 0; i--) {
                Soldier s = soldiers.get(i);
                s.isMove = true;
                if (s.x==nowX && s.y==nowY) {
                    deadSoldier++;
                    soldiers.remove(i);
                }
            }
            updateSoldierMap();

            sb.append(soldierDistance).append(" ")
                    .append(rockSoldier).append(" ")
                    .append(deadSoldier).append("\n");
        }
    }

    static void updateSoldierMap() {
        for (int i = 0; i < N; i++) Arrays.fill(soldierMap[i], false);
        for (Soldier s : soldiers) soldierMap[s.x][s.y] = true;
    }

    static boolean[][] buildVisionMap(int mx, int my, int dir) {
        boolean[][] vis = new boolean[N][N];
        int[][] dxys3 = VISION[dir];
        Deque<int[]> q = new ArrayDeque<>();
        Deque<int[]> occ = new ArrayDeque<>();

        q.add(new int[]{mx, my});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x=cur[0], y=cur[1];
            for (int i = 0; i < 3; i++) {
                int nx=x+dxys3[i][0], ny=y+dxys3[i][1];
                if (outCheck(nx,ny)||vis[nx][ny]) continue;
                if (soldierMap[nx][ny]) {
                    int type;
                    if (nx==mx||ny==my) type=1;
                    else {
                        boolean sameSign=(nx-mx)*dxys3[0][0]>0
                                &&(ny-my)*dxys3[0][1]>0;
                        type = sameSign ? 0 : 2;
                    }
                    occ.add(new int[]{nx,ny,type});
                }
                vis[nx][ny] = true;
                q.add(new int[]{nx,ny});
            }
        }

        while (!occ.isEmpty()) {
            int[] cur = occ.poll();
            int x=cur[0], y=cur[1], t=cur[2];
            for (int d = 0; d < 3; d++) {
                if (t==1&&d!=1) continue;
                if (t==0&&d==2) continue;
                if (t==2&&d==0) continue;
                int nx=x+dxys3[d][0], ny=y+dxys3[d][1];
                if (outCheck(nx,ny)||!vis[nx][ny]) continue;
                vis[nx][ny] = false;
                occ.add(new int[]{nx,ny,t});
            }
        }
        return vis;
    }

    static int countInVis(boolean[][] vis) {
        int cnt = 0;
        for (Soldier s : soldiers)
            if (vis[s.x][s.y]) cnt++;
        return cnt;
    }

    static int[][] bfsFromEnd(int er, int ec) {
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row,-1);
        Deque<int[]> q = new ArrayDeque<>();
        dist[er][ec] = 0;
        q.add(new int[]{er,ec});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx=cur[0]+dx[i], ny=cur[1]+dy[i];
                if (outCheck(nx,ny)||road[nx][ny]==1||dist[nx][ny]!=-1) continue;
                dist[nx][ny]=dist[cur[0]][cur[1]]+1;
                q.add(new int[]{nx,ny});
            }
        }
        return dist;
    }

    static boolean outCheck(int x, int y) {
        return x<0||x>=N||y<0||y>=N;
    }
}