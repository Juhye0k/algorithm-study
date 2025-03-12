import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 문제의 수 N, 이용자 수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        List<Integer> user = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken())); 
        }
        Collections.sort(list);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            user.add(Integer.parseInt(st.nextToken())); 
        }

        for (int i = 0; i < M; i++) {
            int userValue = user.get(i);   
            int left = 0;
            int right = list.size() - 1;
            int ans = 0;             
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (userValue >= list.get(mid)) {
                    ans = mid + 1;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (ans == 0)
                bw.write(0 + " ");
            else {
                int boardSize = (int) Math.floor((3 + Math.sqrt(12 * ans - 3)) / 6);
                bw.write(boardSize + " ");
            }
        }
        bw.flush();
        bw.close();
    }
}
