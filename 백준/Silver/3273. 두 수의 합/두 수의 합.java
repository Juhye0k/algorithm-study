import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int left = 0;
        int right = n-1;
        int[] ar = new int[n];
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        Arrays.sort(ar);
        while(left<right) {
            int result = ar[left] + ar[right];
            if(result == x) {
                answer++;
                right--;
            }
            else if(result < x) {
                left++;
            }
            else {
                right--;
            }
        }
        System.out.println(answer);
    }


}