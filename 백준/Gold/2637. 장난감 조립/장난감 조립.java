import java.io.*;
import java.util.*;

class Node {
    int num;
    int count;

    Node(int num,int count) {
        this.num = num;
        this.count = count;
    }
}

public class Main {
    static List<List<Node>> graph;
    static int[] indegree;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        /*
        기본 부품 : 다른 부품을 사용하여 조립할 수 없음
        중간 부품 : 다른 중간 부품이나 기본 부품을 이용하여 만들어지는 부품
         */

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        indegree = new int[N + 1];
        count = new int[N + 1];
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            graph.get(X).add(new Node(Y,K));
            indegree[Y] ++;
        }
        count[N] = 1;
        bfs(N);
        StringBuilder sb = new StringBuilder();

        // 기본 부품을 오름차순으로 출력
        for (int i = 1; i <= N; i++) {
            if (graph.get(i).isEmpty() && count[i] > 0) {
                sb.append(i).append(" ")
                        .append(count[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
    public static void bfs(int N) {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(Node next : graph.get(cur)) {
                // 기본 숫자 -> 바로 더함
                count[next.num] += count[cur] * next.count;

                indegree[next.num]--;
                if(indegree[next.num] == 0) {
                    q.offer(next.num);
                }
            }
        }
    }

}