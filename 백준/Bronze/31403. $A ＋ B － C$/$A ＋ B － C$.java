import java.io.*;
import java.util.*;


public class Main {
    static char tree[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int A=Integer.parseInt(br.readLine());
        int B=Integer.parseInt(br.readLine());
        int C=Integer.parseInt(br.readLine());
        int result1=A+B-C;
        int result2=Integer.parseInt(A +String.valueOf(B))-C;
        bw.write(result1+"\n");
        bw.write(result2+"\n");
        bw.flush();
        bw.close();


    }


}

