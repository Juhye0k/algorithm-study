import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int vertex;
    int weight;

    public Node(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static List<List<Node>> tree;
    static boolean[] visited;
    static int maxDist;
    static Node maxNode;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;

       // 노드의 개수 n개 입력
       int n = Integer.parseInt(br.readLine());

       tree = new ArrayList<>();
       visited = new boolean[n+1];
       maxDist = -1;
       for(int i=0; i<n+1; i++){
           tree.add(new ArrayList<>());
       }
       for(int i=0; i<n-1; i++){
           st = new StringTokenizer(br.readLine());
           // 부모 노드의 번호
           int parent = Integer.parseInt(st.nextToken());
           // 자식 노드의 번호
           int child = Integer.parseInt(st.nextToken());
           // 간선이 가중치
           int value = Integer.parseInt(st.nextToken());
           // 노드 하나씩마다 탐색하면 시간이 오래 걸릴거 같아서 루트에 있는 노드만 탐색해야할거 같은데 어떻게 알지
           tree.get(parent).add(new Node(child, value));
           tree.get(child).add(new Node(parent, value));
       }
       // 루트에서 DFS 돌려서 가장 길이가 긴 점 찾기
       dfs(new Node(1,0),0);
        // 그 점에서 다시 dfs 돌려서 가장 긴 점 찾기
        visited = new boolean[n+1];
        maxDist = -1;
        dfs(maxNode, 0);
        System.out.println(maxDist);

    }
    public static void dfs(Node node, int result){
        visited[node.vertex] = true;

        if(maxDist<result){
            maxNode = node;
            maxDist = result;
        }

        for(Node temp : tree.get(node.vertex)){
            if(!visited[temp.vertex]){
                dfs(temp, result+temp.weight);
            }
        }
    }
}