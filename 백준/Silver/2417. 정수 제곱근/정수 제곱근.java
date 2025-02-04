import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());
        long left=0;
        long right=(long)Math.sqrt(Long.MAX_VALUE);
        long mid = 0;
        while(left<=right) {
            mid = left + (right - left) / 2;
            if (mid <Math.sqrt(n)) {
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }
        if(mid * mid < n)
        {
            mid++;
        }
        System.out.println(mid);
        br.close();
    }
}
