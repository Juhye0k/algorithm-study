import java.io.*;
import java.util.*;

class Paper{
    int index;
    int importance;
    public Paper(int index, int importance) {
        this.index = index;
        this.importance = importance;
    }
}


public class Main {
        static int N,M;
        static List<int[]> snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Paper> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;

        // 테스트 케이스 입력
        int T=Integer.parseInt(br.readLine());
        // 테스트 케이스만큼 반복문 돌리기
        for(int i=0;i<T;i++){
            // 문서의 개수
            st=new StringTokenizer(br.readLine());
            //문서의 개수 N
            N=Integer.parseInt(st.nextToken());
            // 궁금한 문서 M
            M=Integer.parseInt(st.nextToken());
            // 각 문서의 중요도
            st=new StringTokenizer(br.readLine());
            // 큐에 삽입
            for(int j=0;j<N;j++){
                int importance=Integer.parseInt(st.nextToken());
                queue.add(new Paper(j,importance));
                priorityQueue.add(importance);
            }
            int index=0;
            while(!queue.isEmpty()){
                // 차례 증가
                // 큐애서 하나 빼기
                Paper p=queue.poll();
                // 만약 그 종이가 이번 차례이면
                if(p.importance==priorityQueue.peek()){
                    index++;
                    // 큐를 빼기
                    priorityQueue.poll();
                    // 원하는 인덱스일 때
                    if(p.index==M){
                        bw.write(index+"\n");
                        break;
                    }
                }
                else{
                    queue.add(p);
                }
            }
            queue.clear();
            priorityQueue.clear();
        }
        bw.flush();
        bw.close();
    }

}
