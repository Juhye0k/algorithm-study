import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int end = calculate(C,D);
        int start = calculate(A,B);
        System.out.println(end-start);

    }
    public static int calculate(int hour, int minute) {
        return 60*hour + minute;
    }
}