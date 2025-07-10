import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;
    String status; // 상태
    public Node(int x, int y, String status){
        this.x = x;
        this.y = y;
        this.status = status;
    }
}


public class Main{

   static int N;
   static int[][][] dp;
   static int[][] house;

   public static int solve(Node node){
       int x = node.x;
       int y = node.y;
       String status = node.status;
       int statusIndex = -1;
       if(status.equals("dx"))
           statusIndex = 0;
       if(status.equals("dy"))
           statusIndex = 1;
       if(status.equals("dz"))
           statusIndex = 2;

       // 기저 조건
       if(x<1 || x>N || y<1 || y>N)
           return 0;
       // 가로일 때
       if(dp[x][y][statusIndex] != -1)
           return dp[x][y][statusIndex];
       if(x==N && y==N)
           return 1;
       int result = 0;
       if(status.equals("dx")){
           if(y+1<=N && house[x][y+1]!=1)
               result+=solve(new Node(x, y+1, "dx"));
           if(x+1<=N && y+1<=N && house[x+1][y+1]!=1 && house[x+1][y]!=1 && house[x][y+1]!=1)
               result+=solve(new Node(x+1, y+1, "dz"));
       }
       // 세로일 때
       else if(status.equals("dy")){
           if(x+1<=N && house[x+1][y]!=1)
               result+=solve(new Node(x+1, y, "dy"));
           if(x+1<=N && y+1<=N && house[x+1][y+1]!=1 && house[x][y+1]!=1 && house[x+1][y]!=1)
               result+=solve(new Node(x+1, y+1, "dz"));
       }
       // 대각선일 때
       else if(status.equals("dz")){
           if(y+1<=N && house[x][y+1]!=1)
               result+=solve(new Node(x, y+1, "dx"));
           if(x+1<=N && house[x+1][y]!=1)
               result+=solve(new Node(x+1,y,"dy"));
           if(x+1<=N && y+1<=N && house[x+1][y+1]!=1 && house[x+1][y]!=1 && house[x][y+1]!=1){
               result+=solve(new Node(x+1, y+1, "dz"));
           }
       }
       dp[x][y][statusIndex] = result;
       return dp[x][y][statusIndex];
   }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        // 집의 크기 N
        N = Integer.parseInt(br.readLine());
        house = new int[N+1][N+1];

        for(int i=1; i<=N; i++){ // 집의 상태 주어짐
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N+1][N+1][3];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int k=0; k<3; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        int answer = solve(new Node(1,2,"dx"));

        System.out.println(answer);
    }

}
