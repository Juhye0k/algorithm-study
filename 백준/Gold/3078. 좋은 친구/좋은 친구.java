
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer>[] queues =  new LinkedList[21];
        for(int i= 2; i<=20; i++) {
            queues[i] = new LinkedList<>();
        }
        long goodFriendsCount = 0;
        for(int i=0; i<N; i++) {
            String name = br.readLine();
            int len = name.length();
            // 나랑 이름 길이가 같은 큐를 봤을 때, 가장 오래기다린 사람과
            // 지금 내 등수 차이가 K보다 크면 그 사람은 빼라
            while(!queues[len].isEmpty() && i-queues[len].peek()>K) {
                queues[len].poll();
            }
            goodFriendsCount += queues[len].size();
            queues[len].add(i);
        }
        System.out.println(goodFriendsCount);


    }
}