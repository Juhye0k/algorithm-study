import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {
    static int n;
    static List<List<Integer>> graph;
    static Queue<Integer> queue;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 개수
        n = Integer.parseInt(st.nextToken());
        // 도로의 개수
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        // m개의 줄에 도시가 주어짐
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 도로 정비 계획에 들어가있는 도로의 수 q
        int q = Integer.parseInt(br.readLine());
        // 두 도시 i,j를 잇는 도로
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);

            queue = new LinkedList<>();
            visited = new boolean[n+1];
            int[] distances = bfs(1);
            for(int j=1; j<=n; j++) {
                sb.append(distances[j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[] bfs(int start) {
        int dist[] = new int[n+1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        queue.add(start);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : graph.get(cur)) {
                if(dist[next] == -1){
                    dist[next] = dist[cur] + 1;
                    queue.add(next);
                }
            }
        }
        return dist;
    }


}
