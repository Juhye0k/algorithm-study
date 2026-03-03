
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 아이의 수 N
        int N = Integer.parseInt(st.nextToken());

        // 사탕을 꺼내주는 횟수 M
        int M = Integer.parseInt(st.nextToken());

        long [] ar = new long[M+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            long count = Long.parseLong(st.nextToken());
            ar[i] = ar[i-1] + count;
        }
        for(int i=0; i<N; i++) {
            long target = Long.parseLong(br.readLine());
            if(ar[M]<target) {
                sb.append("Go away!").append("\n");
                continue;
            }
            int result = BinarySearch(ar, target);

            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
    public static int BinarySearch(long[] ar, long target) {
        int left = 1;
        int right = ar.length-1;
        int mid = 0;
        int ans = 0;
        while(left<=right) {
            mid = (left+right)/2;
            if(ar[mid]>=target) {
                right = mid-1;
                ans = mid;
            }
            else {
                left=mid+1;
            }
        }
        return ans;
    }
}