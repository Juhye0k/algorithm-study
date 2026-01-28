

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;
        long [] gom = new long[3];
        long [] ticket = new long[3];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<3; i++) {
            gom[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            ticket[i] = Long.parseLong(st.nextToken());
        }


        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
               long eat = Math.min(gom[j], ticket[j]);
               result += eat;
               gom[j] -= eat;
               ticket[j] -= eat;

               int next = (j+1)%3;
               ticket[next] += ticket[j] / 3;
               ticket[j] %= 3;
            }
        }
        System.out.println(result);
    }
}