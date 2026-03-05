
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ar = new int[N];
        for (int i = 0; i < N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);
        int ans = 0;
        int middleIndex = (N + 1) / 2;

        for(int i=0; i<middleIndex; i++) {
            ans+= check(ar[i]);
        }
        System.out.println(ans+1);
    }
    public static int check(int num) {
        int count = 0;
        while(num>1) {
            num/=2;
            count++;
        }
        return count;
    }

}
