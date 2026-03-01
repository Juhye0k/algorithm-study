
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            String str = br.readLine();
            int length = (int) Math.sqrt(str.length());
            char[][] ar = new char[length][length];
            // 4,0 3,0 2,0 1,0 0,0 4,1
            for(int j=0; j<length; j++) {
                for(int k=0; k<length; k++) {
                    ar[j][k] = str.charAt(j*length+k);
                }
            }

            for(int j=length-1; j>=0; j--) {
                for(int k=0; k<length; k++) {
                    sb.append(ar[k][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

}