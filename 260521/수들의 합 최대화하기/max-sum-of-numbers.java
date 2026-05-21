import java.util.Scanner;
public class Main {
    static int n;
    static boolean[] visited;
    static int ans = 0;
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        visited = new boolean[n];
        choice(0,0);
        System.out.println(ans);
    }
    public static void choice(int row, int sum) {
        int result = 0;
        if(row==n) {
            ans = Math.max(ans,sum);
            return;
        }
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            choice(row+1,sum+grid[row][i]);
            visited[i] = false;
        }
    }
}