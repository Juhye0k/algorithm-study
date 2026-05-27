import java.util.*;
import java.io.*;

public class Main {
    static int[] monthDays = {31, 28, 31, 30, 31, 30, 31,31,30,31,30,31};
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());

        int ans = solve(m2,d2) - solve(m1,d1);

        System.out.println(ans+1);


    }
    public static int solve(int month, int day) {
        int result = 0;
        for(int i=0; i<month-1; i++) {
            result+=monthDays[i];
        }
        return result+day;
    }
}