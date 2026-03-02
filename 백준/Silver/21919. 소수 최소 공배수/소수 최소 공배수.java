


import org.w3c.dom.Node;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];
        boolean[] sosu = tenes();
        long result = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(sosu[num]&&result%num!=0) {
                result*=num;
            }
        }
        if(result==1) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);

    }
        public static boolean[] tenes() {
            boolean[] isPrime = new boolean[10000001];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for(int i=2; i*i<=1000000; i++) {
                for(int j=i*i; j<=1000000; j+=i) {
                    if(isPrime[j]) {
                        isPrime[j] = false;
                    }
                }
            }
            return isPrime;
        }



}