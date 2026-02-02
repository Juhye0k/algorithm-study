


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int value;
    int depth;
    public Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}



public class Main {

    static int N,M,K;
    static int [] ar;
    static int result = 0;
    static int [][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        // 사탕 종류 개수 N
        N = Integer.parseInt(st.nextToken());
        // 최대 M개 가져갈 수 있음
        M = Integer.parseInt(st.nextToken());
        // K로 나눠떨어지기
        K = Integer.parseInt(st.nextToken());
        ar = new int[N];
        // 사탕에 담겨있는 사탕의 개수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        visited = new int[M + 1][K];
        for(int i=0; i<=M; i++) {
            Arrays.fill(visited[i], -1);
        }
        bfs();
        System.out.println(result);
    }
    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        while(!q.isEmpty()) {
            Node temp = q.poll();

            if(temp.value < visited[temp.depth][temp.value%K]) continue;

            if(temp.depth >0 && temp.value%K==0) {
                result = Math.max(result, temp.value);
            }


            if(temp.depth < M) {
                for(int i=0 ;i<N; i++) {
                    int nextValue = temp.value + ar[i];
                    int nextDepth = temp.depth + 1;
                    int nextRem = nextValue%K;
                    if(visited[nextDepth][nextRem] < nextValue) {
                        visited[nextDepth][nextRem] = nextValue;
                        q.offer(new Node(nextValue,nextDepth));
                    }
                }
            }
        }

    }
}