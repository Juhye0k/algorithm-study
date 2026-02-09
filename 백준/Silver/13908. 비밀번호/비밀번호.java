



import javax.sound.sampled.Line;
import java.awt.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;





public class Main {


    static int[] ar;
    static boolean[] check;
    static int n;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 비밀번호 길이 n
        n = Integer.parseInt(st.nextToken());
        // 비밀번호에 들어가는 수 m
        int m = Integer.parseInt(st.nextToken());
        check = new boolean[10];

        if(m>0) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++) {
                int num = Integer.parseInt(st.nextToken());
                check[num] = true;
            }
        }
        ar = new int[n];

        dfs(0);
        System.out.println(answer);

    }
    public static void dfs(int depth) {
        if(depth==n) {
            if(valid()) {
                answer++;
            }
            return;
        }

        for(int i=0; i<10; i++) {
            ar[depth] = i;
            dfs(depth+1);
        }
    }
    public static boolean valid() {
        for(int i=0; i<10; i++) {
            if(check[i]) {
                boolean exists = false;
                for(int j=0; j<n; j++) {
                    if(ar[j]==i){
                        exists = true;
                        break;
                    }
                }
                if(!exists) return false;
            }
        }
        return true;
    }
}