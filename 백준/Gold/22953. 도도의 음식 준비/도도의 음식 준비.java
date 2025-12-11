

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static int[] arr;
    static int N,K,C;
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 요리사의 수
        N = Integer.parseInt(st.nextToken());

        // 만들어야 할 음식의 개수
        K = Integer.parseInt(st.nextToken());

        // 격려해줄 수 있는 횟수
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        // 길이가 N인 정수 수열 A, 음식 조리시간
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 요리사 격려를 어떻게 해야하지???
        // 최소 시간은 음식을 선택하고 난 후 하면 될듯
        dfs(0);
        System.out.println(result);

    }
    public static void dfs(int count) {

        // 격려를 받은 요리사의 리스트


        // 만약 개수가 C개이다 -> 이분탐색 돌려보기
        if(count == C){
            solution();
            return;
        }
        for(int i=0; i<N; i++) {
            // 만약 해당 값이 1보다 크면
            if(arr[i] > 1) {
                arr[i] -=1;
                dfs(count+1);
                arr[i] +=1;
            } else {
                dfs(count+1);
            }
        }

    }
    public static void solution() {
        long left =1;
        long right = 1000000000000L;
        long ans=0;
        while(left<=right) {
            long mid = (left+right)/2;;
            long count =0;
            for(int i=0; i<N; i++) {
                count +=  (mid/arr[i]);
            }
            if(count>= K) {
                ans = mid;
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }
        result = Math.min(ans, result);
    }
}
