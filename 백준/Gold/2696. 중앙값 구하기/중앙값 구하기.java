import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb;
        StringTokenizer st=null;
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        // 테스트 케이수 개수 T
        int T=Integer.parseInt(br.readLine());
        // T만큼 반복문
        for(int t=0;t<T;t++) {
            // 수열의 크기 M
            int M=Integer.parseInt(br.readLine());
            minHeap=new PriorityQueue<>();
            maxHeap=new PriorityQueue<>(Collections.reverseOrder());
            int size=0;
            int printCount=0;
            sb=new StringBuilder();
            while(size<M){
                if(size%10==0){
                    st=new StringTokenizer(br.readLine());
                }
                int num=Integer.parseInt(st.nextToken());
                size++;
                // 일단 maxHeap에 넣음
                maxHeap.add(num);
                // 만약 maxHeap의 사이즈-minHeap의 사이즈>=2 이면, maxHeap 빼서 minHeap에 넣기
                if(maxHeap.size()- minHeap.size()>=2)
                    minHeap.add(maxHeap.poll());
                // 홀수 번일 때
                if(size>=2&& maxHeap.peek() > minHeap.peek()){
                    int temp=maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(temp);
                }
                if(size%2==1){
                    // 중앙값을 차례때로 공백으로 구분하여 출력
                    sb.append(maxHeap.peek()).append(" ");
                    printCount++;
                    if(printCount==10){
                        sb.append("\n");
                    }
                }
            }
            bw.write(printCount+"\n");
            bw.write(sb.toString()+"\n");
        }
        bw.flush();
        bw.close();

    }
}
