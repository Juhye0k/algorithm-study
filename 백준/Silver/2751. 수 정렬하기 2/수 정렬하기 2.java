import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int ar[] = new int[N];
        for (int i = 0; i < N; i++) {
            ar[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ar);
        for (int i : ar) {
            bw.write(i + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
