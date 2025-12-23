

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static int blue = 0, white = 0;
    static int[][] ar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 한 변의 길이 N
        int N = Integer.parseInt(br.readLine());
        ar = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                ar[i][j] = st.nextToken().charAt(0) - '0';
            }
        }
        divide(1,1,N);
        sb.append(white).append("\n").append(blue);
        System.out.println(sb);


    }
    public static void divide(int row, int col, int size) {
        if(colorCheck(row, col, size)) {
            if(ar[row][col]==0)
                white++;
            else
                blue++;
            return;
        }
        int newSize = size/2;
        divide(row,col, newSize);
        divide(row, col+size/2, newSize);
        divide(row+size/2, col, newSize);
        divide(row+newSize, col+newSize, newSize);

    }
    public static boolean colorCheck(int x, int y, int size) {
        int first = ar[x][y];
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(ar[i][j] != first) return false;
            }
        }
        return true;
    }
}