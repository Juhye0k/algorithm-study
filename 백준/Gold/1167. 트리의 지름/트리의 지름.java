import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node{
    int vertex;
    int value;

    public Node(int vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }
}
public class Main {
    static boolean[] visited;
    static List<List<Node>> graph;
    static int maxResult;
    static int maxNode;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       // 트리의 정점의 개수 V
        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<V; i++){
            // 간선 정보
            String[] arr = br.readLine().split(" ");
            int start = Integer.parseInt(arr[0]);
            // -1이 마지막 정보
            for(int j=1; j<arr.length-1;){
                if(arr[j].equals("-1"))
                    break;
                int vertex = Integer.parseInt(arr[j++]);
                int value = Integer.parseInt(arr[j++]);
                graph.get(start).add(new Node(vertex, value));
                graph.get(vertex).add(new Node(start, value));
            }
        }
        visited = new boolean[V+1];
        dfs(1,0);
        visited = new boolean[V+1];
        maxResult = -1;
        dfs(maxNode,0);
        System.out.println(maxResult);

    }
    public static void dfs(int start, int result){
        visited[start] = true;
        if(result> maxResult){
            maxNode= start;
            maxResult = result;
        }

        for(Node node : graph.get(start)){
            if(!visited[node.vertex]){
                dfs(node.vertex, result+node.value);
            }
        }
    }

}