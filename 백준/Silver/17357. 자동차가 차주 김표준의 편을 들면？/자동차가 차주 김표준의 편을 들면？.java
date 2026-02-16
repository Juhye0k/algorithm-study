
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [] x = new int[N+1];
        long [] x2 = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] ar = new int[N+1];
        for(int i=1; i<=N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
            x[i] = x[i-1]+ar[i];
            x2[i] = x2[i-1] + (long) ar[i] *ar[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int k=1; k<=N; k++) {
            int bestIndex = 0;
            double maxVariance = -1.0;

            for(int i=1; i<=N-k+1; i++) {
                int end = i+k-1;
                long sum = x[end] - x[i-1];
                long sqSum = x2[end] - x2[i-1];

                long variance = k * sqSum - sum * sum;

                if(variance>maxVariance) {
                    maxVariance = variance;
                    bestIndex = i;
                }
            }
            sb.append(bestIndex).append("\n");
        }
        System.out.print(sb);
    }
}