
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    long num;
    int count;

    public Node(long num, int count){
        this.num = num;
        this.count = count;
    }
}

public class Main {
    static Queue<Node> queue;
    static long A,B;
    static int minCount;
    static long answer;
    static HashSet<Long> visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue =  new LinkedList<>();
        minCount = Integer.MAX_VALUE;
        visited = new HashSet<>();
        answer = -1L;
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        BFS(new Node(A,1));
        System.out.println(answer);
    }

    public static void BFS(Node node){
        queue.add(node);
        visited.add(node.num);
        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            long n = temp.num;
            int cnt = temp.count;

            if (n == B)
            {
                if(cnt < minCount)
                {
                    minCount = cnt;
                    answer = cnt;
                }

            }
            // 2를 곱한다
            long next1 = n*2;
            if(next1<=B && !visited.contains(next1)){
                visited.add(next1);
                queue.add(new Node(next1,cnt+1));
            }
            // 1을 수의 가장 오른쪽에 추가한다
            long next2 = n * 10 + 1;
            if(next2<=B && !visited.contains(next2)){
                visited.add(next2);
                queue.add(new Node(next2,cnt+1));
            }
        }
    }
}