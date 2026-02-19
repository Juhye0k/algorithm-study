


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 모인 사람의 수 N
        int N = Integer.parseInt(st.nextToken());
        // 옮겨야 하는 상자의 개수 K
        long K = Long.parseLong(st.nextToken());

        int people[] = new int[N];

        st = new StringTokenizer(br.readLine());
        // i번째 사람의 분당 작업 속도
        for(int i=0; i<N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        long maxSpeed = 0;
        // 팀을 나눠야함 -> 팀을 어떻게 나눌 것인가 -> 느린 사람을 한 팀에 몰아주고, 인원수를 작게?
        // 1. 정렬 2. 20만번 팀짜기 3. 계산
        Arrays.sort(people);
        for(int i=0; i<N-1; i++) {
            // 팀 1: 0부터 i까지
            // 팀 2: i+1번부터 N까지
            long team1 = (long) people[0] * (i + 1);
            long team2 = (long) people[i+1] * (N - i - 1);
            long v = team1 + team2;
            maxSpeed = Math.max(maxSpeed, v);

        }
        long ans = K/maxSpeed;
        if(K%maxSpeed!=0)
            ans++;
        System.out.println(ans);
    }
}