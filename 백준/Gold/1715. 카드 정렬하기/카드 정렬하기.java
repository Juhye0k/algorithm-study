import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        // 첫째 줄에 N이 주어진다.
        int N=Integer.parseInt(br.readLine());
        // 총 비교 횟수
        int result=0;
        // N개의 줄에 걸쳐 카드 묶음의 각각의 크기가 주어진다.
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(br.readLine());
            pq.offer(num);
        }
        while(pq.size()>1){
            // 두 장을 뽑는다
            int num1=pq.poll();
            int num2=pq.poll();
            // 두장의 합을 result에 더한다
            int add=num1+num2;
            result+=add;
            // 두 장을 더해서 다시 우선순위 큐에 넣는다
            pq.add(add);
        }
        // result를 출력한다
        System.out.println(result);
    }
}
