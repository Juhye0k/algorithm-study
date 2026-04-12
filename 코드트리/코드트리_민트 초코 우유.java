import java.io.*;
import java.util.*;
class Node implements Comparable<Node>{
    int x,y;
    public Node(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int compareTo(Node o) {
        if(Main.B[this.x][this.y]!=Main.B[o.x][o.y]) {
            return Main.B[o.x][o.y] - Main.B[this.x][this.y];
        }

        if(this.x==o.x) {
            return this.y-o.y;
        }
        return this.x-o.x;

    }
}
class Boss {
    int x,y,foodMask;
    public Boss(int x, int y, int foodMask) {
        this.x=x;
        this.y=y;
        this.foodMask = foodMask;
    }
}

public class Main {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;
    static int[][] B;
    static char[][] F;
    static int N;
    static int[][] food;
    static boolean[][] defended;
    public static void main(String[] args) throws IOException{
        // Please write your code here.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 교실의 크기
        int T = Integer.parseInt(st.nextToken()); // 일수
        food = new int[N][N]; // 초기 신봉 음식
        B = new int[N][N];  // 신앙심
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                food[i][j] = getMask(str.charAt(j));
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int nowCount = 0;
        while(nowCount!=T) {
            // 하루 지나감
            // 아침 -> 모든 학생이 신앙심을 1씩 얻음
            nowCount++;
            up();
            // 점심
            // 인접한 학생들과 신봉 음식이 완전히 같을 때 그룹 , 상하좌우
            // 대표자 정함 1. 신앙심이 가장 큰 사람 2.x좌표가 작은 사람 3. y좌표가 작은 사람
            // 신앙심을 대표에게 1씩 넘김
            List<Boss> leaders = new ArrayList<>();
            defended = new boolean[N][N];

            visited = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) {
                        List<Node> group = bfs(new Node(i,j));
                        Collections.sort(group);
                        Node boss = group.get(0);
                        for(int k=0; k<group.size(); k++) {
                            Node temp = group.get(k);
                            if(k==0) {
                                B[temp.x][temp.y] += group.size()-1;
                            }
                            else {
                                B[temp.x][temp.y] -=1;
                            }
                        }
                        leaders.add(new Boss(boss.x,boss.y,food[boss.x][boss.y]));
                    }
                }
            }
            // 저녁
            // 1.단일 음식
            // 2. 이중 조합
            // 3. 삼중 조합
            leaders.sort((a, b) -> {
                int ac = Integer.bitCount(a.foodMask);
                int bc = Integer.bitCount(b.foodMask);

                if (ac != bc) return ac - bc; // 단일 -> 이중 -> 삼중
                if (B[a.x][a.y] != B[b.x][b.y]) return B[b.x][b.y] - B[a.x][a.y]; // 신앙심 큰 순
                if (a.x != b.x) return a.x - b.x;
                return a.y - b.y;
            });
            for(Boss leader:leaders) {
                if(defended[leader.x][leader.y]) continue;
                spread(leader);
            }
            printAnswer();

        }




    }
    public static void up() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                B[i][j]+=1;
            }
        }
    }
    public static int getMask(char c) {
        if (c == 'T') return 1; // 001
        if (c == 'C') return 2; // 010
        return 4;               // 100
    }
    public static List<Node> bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        List<Node> list = new LinkedList<>();
        int mask = food[start.x][start.y];
        list.add(start);
        q.add(start);
        visited[start.x][start.y] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();

            for(int i=0; i<4; i++) {
                int x= temp.x+dx[i];
                int y= temp.y+dy[i];
                if(x>=0 && x<N && y>=0 && y<N && !visited[x][y] && food[x][y]==mask) {
                    Node next = new Node(x,y);
                    q.add(next);
                    visited[x][y] = true;
                    list.add(next);
                }
            }
        }
        return list;
    }
    public static void spread(Boss leader) {
        int bang = B[leader.x][leader.y]%4;
        int x = B[leader.x][leader.y]-1;
        B[leader.x][leader.y]=1;
        int nowX = leader.x;
        int nowY =leader.y;
        int moveX = 0;
        int moveY =0;
        if(bang==0) {
            moveX=-1; moveY=0;
        }
        else if(bang==1) {
            moveX=1; moveY=0;
        }
        else if(bang==2) {
            moveX=0; moveY=-1;

        }
        else {
            moveX=0; moveY=1;

        }
        int nx = nowX+moveX;
        int ny = nowY+moveY;
        // 같은 경우
        while(nx>=0 && nx<N && ny>=0 &&ny<N && x>0) {
            if(food[nx][ny]==leader.foodMask) {
                nx+=moveX;
                ny+=moveY;
                continue;
            }
            int yBelief = B[nx][ny];
            if(x>yBelief) {
                x-=(yBelief+1);
                B[nx][ny]+=1;
                food[nx][ny] = leader.foodMask;
                defended[nx][ny] = true;
            }
            else {
                B[nx][ny]+=x;
                x=0;
                food[nx][ny]=food[nx][ny]|leader.foodMask;
                defended[nx][ny]=true;
            }
            nx+=moveX;
            ny+=moveY;

        }
    }
    public static void printAnswer() {
        long mintChocoMilk = 0;
        long mintChoco = 0;
        long mintMilk = 0;
        long chocoMilk = 0;
        long milk = 0;
        long choco = 0;
        long mint =0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(food[i][j]==7) mintChocoMilk+=B[i][j];
                else if (food[i][j] == 3) mintChoco += B[i][j];
                else if (food[i][j] == 5) mintMilk += B[i][j];
                else if (food[i][j] == 6) chocoMilk += B[i][j];
                else if (food[i][j] == 4) milk += B[i][j];
                else if (food[i][j] == 2) choco += B[i][j];
                else if (food[i][j] == 1) mint += B[i][j];
            }
        }
        System.out.println( mintChocoMilk + " " +
                mintChoco + " " +
                mintMilk + " " +
                chocoMilk + " " +
                milk + " " +
                choco + " " +
                mint);
    }
}

