


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;


import java.util.StringTokenizer;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // A 진법
        String A =  st.nextToken();

        // B 진법
        String B =  st.nextToken();
        int count = 0;
        long ansX = 0;
        int ansA = 0;
        int ansB = 0;
        for(int i=2; i<=36; i++) {
            for(int j=2; j<=36; j++) {
                if (i==j) continue;

                try{
                    long n1 = Long.parseLong(A,i);
                    long n2 = Long.parseLong(B,j);
                    if(n1==n2) {
                        count++;
                        ansX = n1;
                        ansA = i;
                        ansB = j;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }

            }
        }
        if(count == 0) {
            System.out.println("Impossible");
        }
        else if (count == 1) {
            System.out.println(ansX + " " + ansA + " " + ansB);
        }
        else{
            System.out.println("Multiple");
        }
    }
}