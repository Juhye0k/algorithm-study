import java.io.*;
import java.util.*;

import static java.lang.Math.floor;


public class Main {
        static int N,M;
        static List<int[]> snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb=new StringBuilder();
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        // 정수 N, M, K 공백으로 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());  // 할 일의 개수
        int M=Integer.parseInt(st.nextToken());  // 일을 처리했을 때 감소하는 중요도 M
        int K=Integer.parseInt(st.nextToken());  // 완료한 것으로 간주하는 중요도의 최댓값 K

        // 만족감 result
        int result=0;

        // 걸리는 날의 수
        int day=0;

        // 전날 만족감
        int satisfaction=0;

        // 중요도 정수 Di 입력
        for(int i=0;i<N;i++){
            int x=Integer.parseInt(br.readLine());
            pq.add(x);
        }

        // 우선순위 큐가 빌 때까지 반복
        while(!pq.isEmpty()){
            // 1일 추가
            day++;
            // 우선순위 큐에서 값을 빼기
            int x=pq.poll();
            // y/2+p 계산
            int nowSatisfaction= (int) (floor(satisfaction/2)+x);
            // 만족감 배열 출력에 추가
            sb.append(nowSatisfaction+"\n");
            // 전날 만족감에 현재 만족감 넣기
            satisfaction=nowSatisfaction;
            // 중요도 - M 값이 K보다 크면 큐에 다시 삽입
            if(x-M>K){
                pq.add(x-M);
            }
        }
        // 날짜 출력
        bw.write(day+"\n");
        // 만족감 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
