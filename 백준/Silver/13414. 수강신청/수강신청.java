import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 수강 가능 인원 K
        int K = Integer.parseInt(st.nextToken());
        // 대기 목록의 길이 L
        int L = Integer.parseInt(st.nextToken());
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<L; i++) {
            // 수강신청 받기
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
            queue.offer(str);
            // 중복 데이터가 있으면, 앞에꺼 지워야함
        }
        int count = 0 ;
        for(int i=0; i<L; i++) {
            if(count == K) break;
            String str = queue.poll();
            if(map.get(str) > 1) {
                map.put(str, map.get(str)-1);
            }
            else {
                count++;
                sb.append(str).append("\n");
            }
        }
        System.out.println(sb);
    }
}
