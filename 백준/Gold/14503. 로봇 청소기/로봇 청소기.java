import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
        import java.io.*;

class Robot {
    int x, y;
    int d;

    public Robot(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {

    static boolean[][] visited;
    static int[] d;
    static List<List<Integer>> graph;
    static int[] dx = {-1,0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] ar;
    static int N, M;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ar = new int[N][M];
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Robot robot = new Robot(startX, startY, d);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        while(true) {
            // 청소되지 않은 경우 -> 청소
            if(!visited[robot.x][robot.y]) {
                visited[robot.x][robot.y] = true;
                answer++;
            }
            // 주변 4칸 중 청소되지 않은 빈칸이 없음
            if(check(robot.x, robot.y)) {
                // 한칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아감
                move(robot,0);
                // 뒤쪽 칸이 벽이라 후진할 수 없다면
                if(ar[robot.x][robot.y] == 1) {
                    break;
                }
            }
            else {
                robot.d = (robot.d + 3) % 4;
                move(robot,1);
            }
        }
        System.out.println(answer);
    }
    static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            if (ar[nx][ny] == 0 && !visited[nx][ny]) {
                return false; // 청소할 빈칸이 있음
            }
        }

        return true; // 청소할 빈칸이 없음
    }
    static void move(Robot robot, int front) {
        int x = robot.x;
        int y = robot.y;
        int d = robot.d;
        if(front == 1) {
            // 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx<0 || nx>=N || ny<0 || ny>=M) return;
            if(ar[nx][ny]==0 && !visited[x+dx[d]][y+dy[d]]) {
                robot.x = x+dx[d];
                robot.y = y+dy[d];
            }
        }
        else {
            robot.x = x-dx[d];
            robot.y = y-dy[d];
        }


    }
}
