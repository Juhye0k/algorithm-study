import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;





public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 총 과목의수 N
        int N = Integer.parseInt(br.readLine());
        // 과목의 시간표를 저장할 배열 선언
        long [] subjects = new long[N+1];
        // 수업시간의 수 k, 각 시간
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j=0; j<k; j++) {
                int time = Integer.parseInt(st.nextToken());
                subjects[i] |= (1L <<time);
            }
        }
        // 학생의수 M
        int M = Integer.parseInt(br.readLine());
        // 각 학생들의 비어있는 교시 개수
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            long studentMask = 0;
            for(int j=0; j<p; j++) {
                int time = Integer.parseInt(st.nextToken());
                studentMask |= (1L <<time);
            }

            int count = 0;
            for(int j=0; j<N; j++) {
                if((subjects[j] & studentMask) == subjects[j]) count++;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
