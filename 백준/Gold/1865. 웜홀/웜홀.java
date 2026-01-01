
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


class Node {
    int vertex;
    int weight;
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
public class Main {

    /*
    벨만-포드
    음수 가중치가 있는 그래프에서 최단 경로를 구하거나
    음수 사이클 존재 여부를 판별하는 알고리즘
     */
    static List<List<Node>> graph;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 테스트 케이스의 개수
        int TC = Integer.parseInt(br.readLine());
        for(int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            // 지점의 수 N
            int N = Integer.parseInt(st.nextToken());
            // 도로의 개수 M
            int M = Integer.parseInt(st.nextToken());
            // 웜홀의 개수 W
            int W = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for(int j=0; j<=N; j++) {
                graph.add(new ArrayList<>());
            }
            dist = new long[N+1];
            Arrays.fill(dist, 0);

            // 도로의 정보
            for(int j = 0; j<M; j++) {
                st = new StringTokenizer(br.readLine());
                // 연결된 지점 S,E
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                // 거리는 시간 T
                int T = Integer.parseInt(st.nextToken());
                graph.get(S).add(new Node(E, T));
                graph.get(E).add(new Node(S, T));
            }
            // 웜홀의 정보
            for(int j = 0; j<W; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                graph.get(S).add(new Node(E, -T));
            }

            boolean hasNegativeCycle = false;
            for(int j=1; j<=N; j++) {
                boolean updated = false;
                for(int k=1; k<=N; k++) {
                    for(Node node : graph.get(k)) {
                        if(dist[node.vertex] > dist[k] + node.weight) {
                            dist[node.vertex] = dist[k] + node.weight;
                            updated = true;
                            if(j==N){
                                hasNegativeCycle = true;
                                break;
                            }
                        }
                    }
                }
                if(!updated) break;
            }
            sb.append(hasNegativeCycle ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }
}