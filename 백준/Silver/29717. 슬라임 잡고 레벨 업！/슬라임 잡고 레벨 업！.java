import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            long N = Long.parseLong(br.readLine());
            // 총 획득 경험치
            long totalExp = N * (N + 1) / 2;
            
            long left = 1;
            long right = 1000000000;
            long ans = 1;

            while (left <= right) {
                long mid = left + (right - left) / 2;
                if (mid*(mid-1)<= totalExp) {
                    ans = mid;  
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }
}
