
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 회사 직원 수
        int n = Integer.parseInt(st.nextToken());
        // 최초 칭찬 횟수
        int m =  Integer.parseInt(st.nextToken());
        st =  new StringTokenizer(br.readLine());
        int [] graph = new int[n+1];
        int[] points = new int[n+1];

        // 각 직원들의 상사 번호
        for(int i=1;i<=n;i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start =  Integer.parseInt(st.nextToken());
            int point =   Integer.parseInt(st.nextToken());
            points[start] += point;
        }
        int[] dp = new int[n+1];
        for(int i=2;i<=n;i++) {
            points[i] += points[graph[i]];
        }
        for(int i=1;i<=n;i++)
            sb.append(points[i]).append(" ");


        System.out.println(sb);


    }
}
