import java.io.*;
import java.util.*;

class Ruby{
    int weigh;
    int price;
    public Ruby(int weigh, int price){
        this.weigh = weigh;
        this.price = price;
    }
}

class RubyComparator implements Comparator<Ruby>{
    public int compare(Ruby r1, Ruby r2){
        return r1.weigh-r2.weigh;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 보석을 넣을 우선순위 큐
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(Collections.reverseOrder());
        // N과 K 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());    // 보석의 개수
        int K=Integer.parseInt(st.nextToken());    // 가방의 개수
        long result=0;                              // 결과 변수
        Ruby[] rubies = new Ruby[N];
        int [] bags= new int[K];
        // 보석의 정보 M, V
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int weigh = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            Ruby ruby=new Ruby(weigh,price);
            rubies[i]=ruby;
        }
        // 최대 무게 c
        for(int i=0;i<K;i++){
            int weigh=Integer.parseInt(br.readLine());
            bags[i]=weigh;
        }
        // 오름차순 정렬
        Arrays.sort(rubies,new RubyComparator());
        Arrays.sort(bags);
        int index=0;
        // 가방의 개수만큼 반복문
        for(int bagWeigh:bags){
            // 가방의 무게가 보석의 무게보다 크거나 같을 때까지 돌리기
            while(index<N && bagWeigh>=rubies[index].weigh){
                // 해당하는 보석을 최대힙에 넣기
                priorityQueue.add(rubies[index].price);
                // 보석을 어디까지 넣었는지 관리하기 위해 인덱스
                index++;
            }
            // 최대힙에서 가장 큰 값을 빼서 result에 추가
            if(!priorityQueue.isEmpty()){
                result+= priorityQueue.poll();
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
