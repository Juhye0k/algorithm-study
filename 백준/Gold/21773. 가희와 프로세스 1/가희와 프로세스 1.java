import java.io.*;
import java.util.*;

class Process{
    int id;
    int second;
    int importance;

    public Process(int id, int second, int importance){
        this.id = id;
        this.second = second;
        this.importance = importance;
    }
}

class ProcessComparator implements Comparator<Process>{
    public int compare(Process p1, Process p2){
        if(p1.importance==p2.importance)
            return p1.id-p2.id;
        return p2.importance - p1.importance;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Process> pq=new PriorityQueue<>(new ProcessComparator());
        // T,n 입력
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 초
        int T=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());

        // 프로세스 입력, id / 실행을 마치는데 필요한 시간 / 초기 우선 순위
        for(int i=0; i<n; i++){
            st=new StringTokenizer(br.readLine());
            int id=Integer.parseInt(st.nextToken());
            int second=Integer.parseInt(st.nextToken());
            int importance=Integer.parseInt(st.nextToken());
            Process process=new Process(id, second, importance);
            pq.add(process);
        }
        for(int i=0;i<T; i++){
            // 우선 순위가 가장 큰 프로세스  id를 뽑기
            Process p=pq.poll();
            bw.write(p.id+"\n");
            // 1초 후 id를 제외한 나머지 프로세스들의 우선 순위가 1 증가 --> 상대적인 개념이므로 p의 중요도를 감소
            p.importance--;
            // id의 필요한 시간 1 감소
            p.second--;
            if(p.second!=0){
                pq.offer(p);
            }
            if(pq.isEmpty()){
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
