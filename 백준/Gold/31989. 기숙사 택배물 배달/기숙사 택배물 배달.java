
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 방 개수
        int N = Integer.parseInt(st.nextToken());
        // 택배물 개수
        int M = Integer.parseInt(st.nextToken());
        // 각 방의 거리
        int[] x = new int[2*N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N+1; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        long count = 0;
        long[] ar = new long[2 * N + 1];
        int minIndex = N, maxIndex = N;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 배달해야 하는 방
            int room = Integer.parseInt(st.nextToken())-1;

            if(room<N)
                minIndex = Math.min(minIndex, room);
            if(room>N)
                maxIndex = Math.max(maxIndex, room);
            // 무게
            int weight = Integer.parseInt(st.nextToken());
            ar[room] += weight;
        }
        // 택배 보관실 거리
        int boxRoom = N;
        // 고정 시간 비용
        for(int i=minIndex; i<=maxIndex; i++) {
            if(ar[i]!=0) {
                count+=Math.abs(x[boxRoom]-x[i])*ar[i];
            }
        }
        // 이동 시간 비용
        count+=2*(x[boxRoom]-x[minIndex]);
        count+=2*(x[maxIndex]-x[boxRoom]);

        System.out.println(count);

    }
}
