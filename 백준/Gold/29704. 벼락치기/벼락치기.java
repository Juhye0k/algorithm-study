

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int day;
    int cost;
    public Node(int day, int cost) {
        this.day = day;
        this.cost = cost;
    }
}

public class Main {

    static Node [] ar;
    static boolean[] visited;
    static int T;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        // 문제의 수 N
        int N = Integer.parseInt(st.nextToken());
        // 남은 제출기한 T
        T = Integer.parseInt(st.nextToken());
        ar = new Node[N];
        visited = new boolean[N];
        int fullCost = 0;
        int [] dp = new int[T+1];
        for(int i=0; i<N; i++) {

            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            fullCost += cost;
            for(int j=T; j>=day; j--) {
                dp[j] = Math.max(dp[j], dp[j-day]+cost);
            }
        }
        // 문제 1000개, 남은 제출기한 1000일
        // 순서는 중요하지 않다. 벌금이 최소만 되면 됨

        System.out.println(fullCost-dp[T]);


    }


}