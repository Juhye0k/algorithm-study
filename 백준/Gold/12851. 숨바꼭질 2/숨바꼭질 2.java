import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int vertex;
    int time;

    public Node(int vertex, int time) {
        this.vertex = vertex;
        this.time = time;
    }
}

public class Main {
    static int N, K;
    static int resultTime = Integer.MAX_VALUE;
    static int count = 0;
    static int[] timeArr = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println((N - K) + "\n1");
            return;
        }

        bfs();
        
        System.out.println(resultTime);
        System.out.println(count);
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0));

        Arrays.fill(timeArr, -1);
        timeArr[N] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.time > resultTime) continue;

            if (curr.vertex == K) {
                if (resultTime > curr.time) {
                    resultTime = curr.time;
                    count = 1;
                } else if (resultTime == curr.time) {
                    count++;
                }
                continue;
            }

            int[] nextPositions = {curr.vertex - 1, curr.vertex + 1, curr.vertex * 2};

            for (int next : nextPositions) {
                if (next < 0 || next > 100000) continue;

                if (timeArr[next] == -1 || timeArr[next] == curr.time + 1) {
                    timeArr[next] = curr.time + 1;
                    queue.add(new Node(next, curr.time + 1));
                }
            }
        }
    }
}