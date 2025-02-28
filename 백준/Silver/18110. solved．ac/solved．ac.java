import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            bw.write("0");
            bw.flush();
            return;
        }
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        int cutNum = Math.round((float)(n * 15) / 100);
        long sum = 0;
        int count = n - 2 * cutNum;
        
        for (int i = cutNum; i < n - cutNum; i++) {
            sum += arr[i];
        }
        
        bw.write(String.valueOf(Math.round((float)sum / count)));
        bw.flush();
        bw.close();
    }
}
