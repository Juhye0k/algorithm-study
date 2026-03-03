
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 크기 N
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int orResult = 0;
        int start = 1;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if((num | K) !=K) {
                orResult = 0;
                start = i+1;
            }
            else {
                orResult|=num;
                if(orResult == K) {
                    System.out.println(start+" "+i);
                    return;
                }

            }
        }
        System.out.println(-1);
    }
}