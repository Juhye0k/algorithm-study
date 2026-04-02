
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node{
    int num;
    int count;
    public Node(int num, int count){
        this.num = num;
        this.count = count;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans =0;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++) {
            q.add(i);
        }
        while(q.size()>1) {
            if(q.size()<=K) {
                ans = q.poll();
                break;
            }
            // 첫 번째 청설모 뺌
            q.add(q.poll());
            for(int i=2; i<=K; i++) {
                q.poll();
            }
        }
        System.out.println(ans);

    }
}

