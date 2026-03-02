


import org.w3c.dom.Node;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        if(end>=10000000)
            end=10000000;
        boolean[] isPrime = tenes();
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        for(int i=start; i<=end; i++) {
            if(isPrime[i]&&isPalindrome(i)) {
                sb.append(i).append("\n");
            }
        }

        sb.append(-1);
        System.out.println(sb);



    }
    public static boolean isPalindrome(int num) {
          int original = num;
          int reverse = 0;
          while(num>0) {
              reverse = reverse*10 + (num %10);
              num/=10;
          }
          return original == reverse;


    }
        public static boolean[] tenes() {
            boolean[] isPrime = new boolean[end+1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for(int i=2; i*i<=end; i++) {
                for(int j=i*i; j<=end; j+=i) {
                    if(isPrime[j]) {
                        isPrime[j] = false;
                    }
                }
            }
            return isPrime;
        }



}