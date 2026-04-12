import java.util.*;
import java.io.*;
class Node {
    int number;
    int sum;
    List<Point> shape;
    public Node(int number,int sum, List<Point> shape) {
        this.number = number;
        this.sum = sum;
        this.shape = shape;
    }
}
class Point {
    int x,y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int[][] ar;
    static int N;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 격자의 크기
        N = Integer.parseInt(st.nextToken());

        // 실험 진행 횟수
        int Q = Integer.parseInt(st.nextToken());
        ar = new int[N][N];
        int sink=1;
        for(int i=0; i<Q; i++) {
            int result = 0;
            st = new StringTokenizer(br.readLine());

            // 좌측 하단좌표
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            // 우측 상단좌표
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            // 미생물의 넓이 구하기
            // 1. 미생물 투입
            // 다른 미생물이 존재하면, 새로운 미생물이 잡아먹음 -> 그냥 덮어씌우기
            // 잡아먹힌 미생물 기록
            Set<Integer> die = new HashSet<>();
            for(int row = r1; row<r2; row ++) {
                for(int col = c1; col<c2; col++) {
                    if(ar[row][col]!=0) {
                        die.add(ar[row][col]);
                    }
                    ar[row][col] = sink;
                }
            }
            sink++;
            // 이때 기존 영역이 둘 이상으로 나눠질 경우, A 무리의 미생물은 용기에서 모두 사라짐
            for(int num : die) {
                if(check(num)) {
                    remove(num);
                }
            }


            // 살아남은 아이들을 다시 카운팅
            PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> {
                if(a.sum == b.sum)
                    return a.number-b.number;
                return b.sum-a.sum;
            });
            // 좌표기록 + 넓이 + 순번
            boolean[][] visited = new boolean[N][N];
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    if(!visited[j][k] && ar[j][k]!=0) {
                        Node node =extent(j, k, visited);
                        pq.add(node);
                    }
                }
            }
            // 새로운 배열의 크기를 정의


            // 2. 배양 용기 이동
            // 새로운 배양 용기를 정의
            int[][] newArr = new int[N][N];
            Map<Integer,Integer> map = new HashMap<>();
            while(!pq.isEmpty()) {
                Node temp = pq.poll();
                map.put(temp.number,temp.sum);

                int minX=N+1, minY=N+1;
                for(Point p : temp.shape) {
                    minX = Math.min(p.x,minX);
                    minY = Math.min(p.y,minY);
                }
                for(Point p : temp.shape) {
                    p.x-=minX;
                    p.y-=minY;
                }

                // x좌표부터 돌아가며 가능한지 확인
                boolean can = false;
                for(int j=0; j<N; j++) {
                    for(int k=0; k<N; k++) {
                        if(isCan(j,k,temp.shape,newArr)) {
                            can = true;
                            for(Point p : temp.shape) {
                                newArr[p.x+j][p.y+k] = temp.number;
                            }
                            break;
                        }
                    }
                    if(can) break;
                }
            }
            ar = newArr;
            // 3. 실험 결과
            // 상하좌우로 맞닿은 면 -> 인접한 무리
            // 인접한 미생물의 넓이를 서로 곱해서 더하기
            Set<String> checkSet = new HashSet<>();
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    int me = newArr[j][k];
                    if(me==0) continue;
                    for(int m=0; m<4; m++) {
                        int nx = j+dx[m];
                        int ny = k+dy[m];
                        if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                        String key = Math.min(me,newArr[nx][ny])+"-"+Math.max(me,newArr[nx][ny]);
                        if(newArr[nx][ny]!=0 && newArr[nx][ny]!=me && !checkSet.contains(key)) {
                            result+=map.get(me)*map.get(newArr[nx][ny]);
                            checkSet.add(key);
                        }
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    public static boolean check(int num) {
        boolean[][] visited = new boolean[N][N];
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(ar[i][j]==num && !visited[i][j]) {
                    count++;
                    Queue<Point> q = new LinkedList<>();
                    visited[i][j] = true;
                    q.add(new Point(i,j));
                    while(!q.isEmpty()) {
                        Point temp = q.poll();
                        for(int k=0; k<4; k++) {
                            int nx = temp.x + dx[k];
                            int ny = temp.y + dy[k];
                            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]&& ar[nx][ny]==num) {
                                q.add(new Point(nx,ny));
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        if(count>=2) return true;
        return false;
    }
    public static void remove(int num) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(ar[i][j]==num)
                    ar[i][j] = 0;
            }
        }
    }
    public static Node extent(int x, int y, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Point(x,y));
        int sum = 0;
        Node node = new Node(ar[x][y],sum,new LinkedList<>());
        sum++;
        node.shape.add(new Point(x,y));
        while(!q.isEmpty()) {
            Point temp = q.poll();
            for(int i=0; i<4; i++) {
                int nx = temp.x+dx[i];
                int ny = temp.y+dy[i];
                if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && ar[nx][ny]==ar[x][y]) {
                    q.add(new Point(nx,ny));
                    visited[nx][ny] = true;
                    sum++;
                    node.shape.add(new Point(nx,ny));
                }
            }
        }
        node.sum=sum;
        return node;
    }
    public static boolean isCan(int x, int y, List<Point> shape, int[][] newArr) {
        for(Point p :shape) {
            int nx = p.x+x;
            int ny = p.y+y;
            if(nx<0 || nx>=N || ny<0 || ny>=N) return false;
            if(newArr[nx][ny]!=0) return false;
        }
        return true;
    }
}