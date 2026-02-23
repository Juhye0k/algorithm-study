


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;


import java.util.HashSet;
import java.util.StringTokenizer;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 수강생 수 N
        int N = Integer.parseInt(st.nextToken());
        // 시험 범위 챕터 수 M
        int M = Integer.parseInt(st.nextToken());
        HashSet<Integer> studiedSet = new HashSet<>();
        boolean isPossible = true;
        // 각 수강생이 공부한 챕터
        for(int i = 0; i < N; i++){
            st =  new StringTokenizer(br.readLine());
            // 공부한 챕터의 개수
            int count = Integer.parseInt(st.nextToken());
            // 챕터를 이진수로 표현
            int bitMask = 0;
            for (int j =0; j<count; j++) {
                int chapter =Integer.parseInt(st.nextToken());

                bitMask |= (1<<(chapter-1));
            }
            if(!studiedSet.add(bitMask)){
                isPossible = false;
            }
        }
        if(!isPossible){
            System.out.println("-1");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                // 1 << i 는 2의 i승을 의미합니다 (1, 2, 4, 8 ...)
                sb.append(1 << i).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}