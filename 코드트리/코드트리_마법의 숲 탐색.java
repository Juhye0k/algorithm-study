import java.util.*;
import java.io.*;
class Node {
    int x,y;
    int dist;
    Node(int x, int y, int dist) {
        this.x=x;
        this.y=y;
        this.dist=dist;
    }
}
class Point {
    boolean isDist;
    int num;
    Point(int num, boolean isDist) {
        this.num=num;
        this.isDist=isDist;
    }
}

public class Main {
    static Point[][] ar;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int R,C;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int index = 0;
        // 숲의 크기 R,C
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ar = new Point[R+4][C+1];
        // 정령의 수 K
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        for(int i=0; i<K; i++) {
            index++;
            st = new StringTokenizer(br.readLine());
            // 골렘의 출발 열
            int startR = 2;
            int startC = Integer.parseInt(st.nextToken());
            // 골렘의 출구 방향 정보 di
            int golemOut = Integer.parseInt(st.nextToken());
            Node node = new Node(startR,startC,golemOut);
            // 골렘의 이동
            // (1) 아래에 초록색 칸이 비어있으면, 남쪽으로 한칸 내려간다


            while(true) {
                boolean isLeft = false;
                boolean isRight = false;

                // 만약 가로 이동이 가능하면
                if(downPossible(node.x,node.y)) {
                    node.x++;
                }

                // 왼쪽 가능한데 아래도 가능하면
                else if(leftPossible(node.x,node.y)) {
                    node.y--;
                    node.x++;
                    rotateLeft(node);
                }

                // (6) 아래로 내려갈 수 있으면, 반시계방향 회전 후 아래로 한 칸
                else if(rightPossible(node.x, node.y)) {
                    node.y++;
                    node.x++;
                    rotateRight(node);
                }
                else {
                    break;
                }
            }
            if(isOut(node.x,node.y)) {
                ar = new Point[R+4][C+1];
                continue;
            }
            // (7) 골렘이 남쪽에 이동해 이동할 수 없으면 이제 정령을 골렘 내에서 이동시킴
            // (8) 갈 수 있는 칸 중 남쪽의 칸으로 이동하고 종료. 행번호를 누적해야함
            boolean[][] visited = new boolean[R+1][C+1];
            ar[node.x][node.y] = new Point(index, false);
            for(int j=0; j<4; j++) {
                int nx = node.x+dx[j];
                int ny = node.y+dy[j];
                if(node.dist==j) {
                    ar[nx][ny] = new Point(index,true);
                }
                else
                    ar[nx][ny] = new Point(index,false);
            }

            // 도착하면, 골렘에서 이동시키기
            // 이때 골렘의 몸 일부가 숲을 벗어난 상태면, 전부 비우고 새로 시작
            int result = finish(node.x,node.y);
            answer+=result;
        }

        System.out.println(answer);
    }
    // 아래로 내려갈 수 있는지 확인하는 함수
    public static boolean downPossible(int x, int y) {
        int[] dx = {1,2,1};
        int[] dy = {-1,0,1};
        for(int i=0; i<3; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(!check(nx,ny)|| ar[nx][ny]!=null) return false;
        }
        return true;
    }
    // 서쪽에 비어있는지 확인하는 함수
    public static boolean leftPossible(int x, int y) {
        int[] mx = {-1, 0, 1, 1, 2};
        int[] my = {-1, -2, -1, -2, -1};

        for (int i = 0; i < 5; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];
            if (!check(nx, ny) || ar[nx][ny] != null) return false;
        }
        return true;
    }
    public static boolean rightPossible(int x, int y) {
        int[] mx = {-1, 0, 1, 1, 2};
        int[] my = {1, 2, 1, 2, 1};

        for (int i = 0; i < 5; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];
            if (!check(nx, ny) || ar[nx][ny] != null) return false;
        }
        return true;
    }
    // 동쪽으로 이동할 수 있는지 확인하는 함수
    public static boolean check(int x, int y) {
        return x>=1 && x<=R+3 && y>=1 && y<=C;
    }
    // 시계방향 회전
    public static void rotateLeft(Node node) {
        if(node.dist == 0)
            node.dist = 3;
        else
            node.dist-=1;
    }
    public static void rotateRight(Node node) {
        if(node.dist == 3)
            node.dist = 0;
        else
            node.dist+=1;
    }
    public static int finish(int x, int y) {
        boolean[][] visited = new boolean[R+4][C+1];
        int maxX = x;
        // bfs 돌리기.  최솟값 갱신
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visited[x][y] = true;
        // 범위 벗어나면 out
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            maxX = Math.max(maxX,tempX);
            for(int i=0; i<4; i++) {
                int nx = tempX+dx[i];
                int ny = tempY+dy[i];
                if(!check(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(ar[nx][ny]==null) continue;
                // 만약 같은 골렘이다 -> 그냥 이동
                if(ar[tempX][tempY].num == ar[nx][ny].num || ar[tempX][tempY].isDist)  {
                    q.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return maxX-3;

    }
    public static boolean isOut(int x, int y) {
        if (!inForest(x, y)) return true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inForest(nx, ny)) return true;
        }
        return false;
    }
    public static boolean inForest(int x, int y) {
        return x >= 4 && x <= R + 3 && y >= 1 && y <= C;
    }
}