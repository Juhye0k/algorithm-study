import java.io.*;
import java.util.*;

public class Main {



    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));


        // 건물의 종류 N
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] ws = new int[N+1];
        int[] ar = new int[N+1];

        graph = new ArrayList<>();
        visited = new boolean[N+1];
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            ar[i] = Integer.parseInt(arr[0]);
            if(arr.length > 2) {
                for(int j=1; j<arr.length; j++) {
                    int num = Integer.parseInt(arr[j]);
                    if(num == -1) break;
                    graph.get(num).add(i);
                    ws[i]++;
                }
            }
        }
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            if(ws[i] == 0) {
                q.offer(i);
                dp[i] = ar[i];
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph.get(cur)) {
                dp[next] = Math.max(dp[cur]+ar[next], dp[next]);
                ws[next]--;
                if(ws[next] == 0) q.offer(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(dp[i]).append("\n");
        }
        System.out.println(sb);

    }
}