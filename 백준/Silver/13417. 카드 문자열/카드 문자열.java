
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Deque<String> deque = new LinkedList<>();
        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            String[] ar = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                String s =  st.nextToken();
                ar[j] = s;
            }
            // 맨 처음꺼 가져옴
            deque.addFirst(ar[0]);
            // 카드를 왼쪽에서부터 차례대로 가져온다
            for(int j=1; j<N; j++){
                String s =  ar[j];
                String first = deque.peekFirst();
                if(s.compareTo(first)>0) {
                    deque.addLast(s);
                }
                else {
                    deque.addFirst(s);
                }
            }
            while(!deque.isEmpty()){
                sb.append(deque.removeFirst());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}