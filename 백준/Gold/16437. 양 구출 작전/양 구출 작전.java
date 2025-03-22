import java.io.*;
import java.util.*;

class Node {
    int vertex;
    String animal;
    long count;
    int end;

    public Node(int vertex, String animal, long count, int end) {
        this.vertex = vertex;
        this.animal = animal;
        this.count = count;
        this.end = end;
    }
}

public class Main {
    static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 섬의 개수 N
        int N = Integer.parseInt(br.readLine());

        // 인덱스 0부터 N까지 초기화 (1번 섬부터 N번 섬)
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 2번 섬부터 N번 섬까지 정보 입력
        // 각 섬의 정보: 동물(S/W), 마리 수, 부모 섬 번호
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            long count = Long.parseLong(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            // 부모 노드의 자식 리스트에 현재 노드 추가
            graph.get(parent).add(new Node(i, s, count, parent));
        }

        long result = dfs(1);
        System.out.println(result);
    }
    
    public static long dfs(int cur) {
        long sum = 0;
        for (Node child : graph.get(cur)) {
            long temp = dfs(child.vertex);
            // 자식 노드의 동물 정보에 따라 계산
            if(child.animal.equals("S")) {
                temp += child.count;
            } else if(child.animal.equals("W")) {
                temp = Math.max(temp - child.count, 0);
            }
            sum += temp;
        }
        return sum;
    }
}
