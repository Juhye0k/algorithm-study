




import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;


import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node implements Comparable<Node>{
    int index;
    int value;
    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        if(this.value == o.value){
            return this.index - o.index;
        }
        return this.value - o.value;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 크기 N
        int N = Integer.parseInt(br.readLine());
        int [] ar = new int[N+1];
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 수열이 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            ar[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Node(i, ar[i]));
        }
        // 쿼리의 개수 M
        int M =  Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int cal = Integer.parseInt(st.nextToken());
            // 1일 때 -> Ai를 v로 바꿈
            if(cal==1) {
                int a =  Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ar[a] = b;
                pq.offer(new Node(a, b));
            }
            // 2일 때 -> 작은 값의 인덱스 출력
            else {
                while(pq.peek().value != ar[pq.peek().index]) {
                    pq.poll();
                }
                sb.append(pq.peek().index).append("\n");
            }
        }
        System.out.println(sb);
    }
}