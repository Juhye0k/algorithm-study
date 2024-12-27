import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static List<List<Integer>> graph;
    static int[] order; 
    static int N, M, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()); 

        graph = new ArrayList<>();
        visited = new boolean[N + 1];
        order = new int[N + 1];
        count = 1;

        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

    
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

       
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        
        DFS(R);

       
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(order[i]).append("\n");
        }
        System.out.print(sb);
    }

    public static void DFS(int start) {
        visited[start] = true;
        order[start] = count++; 
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                DFS(neighbor);
            }
        }
    }
}
