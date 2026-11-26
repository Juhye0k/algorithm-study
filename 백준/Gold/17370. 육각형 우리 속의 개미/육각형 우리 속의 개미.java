import java.io.*;
import java.util.*;


public class Main {

    static final int[] DX = {0,1,1,0,-1,-1};
    static final int[] DY = {-2, -1, 1, 2, 1, -1};

    static int n;
    static int answer;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int center = 2 *(n+1);
        int size = 2 * center + 1;
        visited = new boolean[size][size];
        visited[center][center] = true;
        dfs(center + DX[0], center + DY[0], 0, 0);

        System.out.println(answer);

    }
    public static void dfs(int x, int y,int dir,int turns){
        if(visited[y][x]) {
            if (turns == n) {
                answer++;
            }
            return;
        }

        if (turns == n) {
            return;
        }
        visited[y][x] = true;
        int left = (dir+5) % 6;
        int right = (dir+1) % 6;
        dfs(x + DX[left], y + DY[left], left, turns + 1);
        dfs(x + DX[right], y + DY[right], right, turns + 1);
        visited[y][x] = false;
    }


}