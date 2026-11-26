import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] ar = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MIN_VALUE;

        int left = 0;
        int right = K;
        int result = 0;
        for(int i=0; i<K; i++) {
            result += ar[i];
        }
        answer = Math.max(answer, result);

        for(int i=1; i<N-K+1; i++) {
            result -= ar[left];
            result += ar[right];
            answer = Math.max(answer, result);
            left++;
            right++;
        }
        System.out.println(answer);
    }


}