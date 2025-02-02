import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken()); // 게임 횟수
        long Y = Long.parseLong(st.nextToken()); // 이긴 게임
        int Z = (int)((double) Y * 100 / X);

        // 승률이 99% 이상이면 승률을 바꿀 수 없음
        if(Z >= 99) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }

        long left = 0;
        long right = 1000000000; 
        long mid = 0;
        while(left <= right) {
            mid = left + (right - left) / 2;
            int newZ = (int)((double) (Y + mid) * 100 / (X + mid)); // 추가 게임 후 승률
            if(newZ <= Z) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(left));
        bw.flush();
        bw.close();
    }
}
