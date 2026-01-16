
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int start;
    int end;
    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Node o) {
        return this.start - o.start;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전깃줄 개수 N
        int N = Integer.parseInt(br.readLine());
        int []dp = new int[N];
        List<Node> node = new ArrayList<>();
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            node.add(new Node(start, end));
        }

        Collections.sort(node);
        for(int i=0; i<N; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(node.get(i).end>=node.get(j).end) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int answer = 0;
        for(int i=0; i<N; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(N-answer);
    }
}
