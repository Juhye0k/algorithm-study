import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
        import java.io.*;



public class Main {

    static boolean[] visited;
    static int[] d;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        d = new int[N+1];
        graph = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            d[second] ++;

            graph.get(first).add(second);
            graph.get(second).add(first);

        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();


        for(int i=1; i<=N; i++) {
            if (!visited[i] && d[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");
            for (int next : graph.get(cur)) {
                d[next]--;
                if(!visited[next] && d[next]==0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}
