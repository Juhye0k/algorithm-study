import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 우선 순위 큐를 정의하여 공격력이 높은 카드가 먼저 나오도록 설정함
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        // H 입력
        int H=Integer.parseInt(br.readLine());
        // N과 Q가 공백으로 구분
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        // 처음 가지고 있는 카드들의 공격력을 나타내는 N개의 정수
        st = new StringTokenizer(br.readLine());
        long sum=0; // 우선 순위 큐에 들어있는 숫자들의 합을 계산하기 위한 sum
        for (int i = 0; i < N; i++) {  // 우선 순위 큐에 초기 카드들을 넣어둠
            int x=Integer.parseInt(st.nextToken());
            pq.add(x);
            sum+=x;
        }
        for(int i=0;i<=Q;i++){
            // i가 1 이상일 때만 카드 넣기
            if(i>=1){
                int num=Integer.parseInt(br.readLine());
                pq.add(num);
                sum+=num;
            }
            // 만약 sum이 h보다 작으면 -1 출력
            if(sum<H){
                bw.write("-1\n");
                continue;
            }
            // 만약 sum-pq의 peek값이 H보다 크면 peek는 필요없는 수이므로 빼기
            while(!pq.isEmpty()&&sum-pq.peek()>=H){
                sum-=pq.poll();
            }
            // 이후 pq의 사이즈가 해당 값이다
            bw.write(pq.size()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
