
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


       // 통로의 길이 N
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long A[] = new long[N];
        long B[] = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 잉크지수
        for(int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        st= new StringTokenizer(br.readLine());

        // 점도 지수, 오름차순으로 주어짐
        for(int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        // i번째 타일 -> 현재 위치보다 오른쪽이면서, 점도지수가 현재 위치인 Ai 이하인 칸을 칠할 수 있음
        for(int i = 0; i < N; i++) {
            // 현재 칸의 잉크지수를 가져옴
            int count = 0;
            long cur = A[i];
            int index = i;
            int result = binarySearch(cur,B);
            count = result-index;
            sb.append(count+" ");
        }
        System.out.println(sb);
    }
    public static int binarySearch(long num, long[] B) {
        int left = 0 ;
        int right = B.length-1;
        int mid = 0;
        int ans = 0;
        while(left <= right) {
            mid = (left+right)/2;
            if(B[mid]>num){
                right=mid-1;
            }
            else {
                left=mid+1;
                ans=mid;
            }
        }
        return ans;
    }

}