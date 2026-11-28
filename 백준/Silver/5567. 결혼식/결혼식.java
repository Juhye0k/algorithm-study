import java.io.*;
import java.sql.SQLOutput;
import java.util.*;





public class Main {

    static int[][] ar;

    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[n+1];
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);

        }
        int answer = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int num : graph.get(1)) {
            q.add(num);
            visited[num] = true;
            answer++;
        }
        while(!q.isEmpty()) {
            int num = q.poll();
            for (int next : graph.get(num)) {
                if(next!=1 && !visited[next]) {
                    answer++;
                    visited[next] = true;
                }
            }
        }
        System.out.println(answer);
    }
}