import java.util.*;
import java.io.*;

class Node {
    int x,y;
    public Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
public class Main {
   
    static int[][] ar;
    static List<Node> list;
    static int[][] bomb;
    static int ans = 0;
    static int N;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[] dx1 = {1,-1,-1,1};
    static int[] dy1 = {1,1,-1,-1};
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new LinkedList<>();
        ar = new int[N][N];
        bomb = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
                if(ar[i][j]==1)
                    list.add(new Node(i,j));
            }
        }
        choice(0);
        System.out.println(ans);
    }
    public static void choice(int index) {
        if(index == list.size()) {
            int result = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++)
                    if(bomb[i][j]>=1) result++;
            }
            ans = Math.max(ans,result);
            return;
        }
        for(int i=0; i<3; i++) {
            mark(i,index,true);
            choice(index+1);
            mark(i,index,false);
        }
    }
    public static void mark(int i, int index,boolean check) {
        Node temp = list.get(index);
        int num = 1;
        if(!check) num = -1;
        int x = temp.x;
        int y = temp.y;
        bomb[x][y] += num;
        if(i==0) {
            for(int j=1; j<=2; j++) {
                int nx = x-j;
                if(check(nx,y)) break;
                bomb[nx][y] += num;
            }
             for(int j=1; j<=2; j++) {
                int nx = x+j;
                if(check(nx,y)) break;
                bomb[nx][y] += num;
            }
        }
        else if(i==1) {
            for(int j=0; j<4; j++) {
                int nx = temp.x+dx[j];
                int ny = temp.y+dy[j];
                if(check(nx,ny)) continue;
                bomb[nx][ny] += num;
            }
        }
        else {
            for(int j=0; j<4; j++) {
                int nx = temp.x+dx1[j];
                int ny = temp.y+dy1[j];
                if(check(nx,ny)) continue;
                bomb[nx][ny] += num;
            }
        }
    }
    
    public static boolean check(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }
}