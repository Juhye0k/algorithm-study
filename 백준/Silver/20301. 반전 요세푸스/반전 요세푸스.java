
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            deque.add(i);
        }
        boolean check = true;
        int count = 0;
        int reverse = 0;
        while(!deque.isEmpty()){
            int num = 0;
            count++;
            if(check) {
                num = deque.removeFirst();
            }
            else {
                num = deque.removeLast();
            }
            if(count<K && check) {
                deque.addLast(num);

            }
            else if(count<K && !check) {
                deque.addFirst(num);
            }

            if(count==K) {
                sb.append(num).append("\n");
                count = 0;
                reverse++;
            }
            if(reverse==M) {
                if(check) check=false;
                else check=true;
                reverse = 0;
            }
        }
        System.out.println(sb);

    }

}