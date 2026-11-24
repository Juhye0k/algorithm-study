import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            String s = br.readLine();
            String[] str = s.split(" ");
            int start = caluclate(str[0]);
            int end = caluclate(str[1]);

            list.add(new int[]{start,end});
        }
        int answer = 0;
        Collections.sort(list, (a,b)->a[0]-b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            int start = list.get(i)[0];

            if(!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            pq.offer(list.get(i)[1]);
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);


    }
    public static int caluclate(String s) {
        String[] str = s.split("\\.");
        String[] str2 = str[0].split(":");

        // 시각
        int hour = Integer.parseInt(str2[0]) * 60 * 60 * 1000;
        // 분
        int minute = Integer.parseInt(str2[1]) * 60 * 1000;
        // 초
        int second = Integer.parseInt(str2[2]) * 1000;
        // 밀리초
        int millisecond = Integer.parseInt(str[1]);
        return hour + minute + second + millisecond;
    }
}