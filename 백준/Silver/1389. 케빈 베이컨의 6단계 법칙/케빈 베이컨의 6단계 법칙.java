


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node implements Comparable<Node> {
    int x;
    int value;
    public Node(int x, int y) {
        this.x = x;
        this.value = y;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}

public class Main {


    static List<List<Integer>> graph;
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        // 유저의 수 N
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        // 친구 관계의 수 M
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int answer =0;
        int result=Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            int ans = bfs(i);
            if(ans<result) {
                result = ans;
                answer = i;
            }
        }
        System.out.println(answer);




    }
    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        int dist[] = new int[N+1];
        Arrays.fill(dist, -1);
        queue.add(start);
        dist[start] = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : graph.get(cur)) {
                if(dist[next]==-1) {
                    queue.add(next);
                    dist[next] = dist[cur] + 1;
                }
            }
        }
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            sum += dist[i];
        }
        return sum;
    }

}