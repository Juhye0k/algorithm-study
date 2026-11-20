import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] ar = new int[N];

        int left = 0 ;
        int right = N-1;

        int ans1 =0, ans2 = 0;
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);
        int ans = Integer.MAX_VALUE;
        while(left < right) {
            int result = ar[left] + ar[right];
            int sum = Math.abs(ar[left] + ar[right]);

            if(result == 0) {
                ans1 = ar[left];
                ans2 = ar[right];
                System.out.println(ans1 + " " + ans2);
                return;
            }
            else if(result > 0) {
                if(ans>sum) {
                    ans = sum;
                    ans1 = ar[left];
                    ans2 = ar[right];
                }
                right--;
            }
            else {
                if(ans>sum) {
                    ans = sum;
                    ans1 = ar[left];
                    ans2 = ar[right];
                }
                left++;
            }
        }
        System.out.println(ans1 + " " + ans2);
    }
}
