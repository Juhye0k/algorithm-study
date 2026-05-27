import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int r1 = solve(A,B,C);
        int r2 = solve(11,11,11);
        if(r1<r2) {
            System.out.println(-1);
            return;
        }
        int ans = r1-r2;
        System.out.println(ans);

    }
    public static int solve(int day, int hour, int minute) {
        int r1 = 60 * 24 * day;
        int r2 = 60 * hour;
        return r1+r2+minute;
    }
}