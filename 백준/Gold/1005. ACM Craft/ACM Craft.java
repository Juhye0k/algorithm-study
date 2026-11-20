import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;



public class Main {


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        for(int test=0;test<T;test++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 건물의 개수 N
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }

            // 아직 처리하지 않은 선행 건물
            int[] indegree =new int[N+1];

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
                indegree[y]++;
            }

            int W = Integer.parseInt(br.readLine());

            int[] dp = new int[N+1];
            Queue<Integer> q = new ArrayDeque<>();

            for(int i=1; i<=N ;i++) {
                if(indegree[i] == 0) {
                    dp[i] = time[i];
                    q.offer(i);
                }
            }
            while(!q.isEmpty()) {
                int cur = q.poll();
                for (int next : graph.get(cur)) {
                    dp[next] = Math.max(dp[next], dp[cur] + time[next]);

                    indegree[next]--;

                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            answer.append(dp[W]).append("\n");


        }
        System.out.println(answer);
    }
}
