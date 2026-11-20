import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        String[][] ar = new String[N][1000];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                ar[i][j] = str.substring(j,j+1);
            }
        }
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new int[]{i,0});
        }
        for(int i=1; i<1000; i++) {
            list.add(new int[]{N-1,i});
        }
        for(int i=0; i<list.size(); i++) {
            StringBuilder T = new StringBuilder();
            int nowX = list.get(i)[0];
            int nowY = list.get(i)[1];
            while(true) {
                if(nowX<0 || nowY>=1000) break;
                if(ar[nowX][nowY]!=null) T.append(ar[nowX][nowY]);
                nowX = nowX-1;
                nowY = nowY+1;
            }
            // 앞,뒤로 KUMOH가 등장하는지 검사
            String s = T.toString();
            int c1 = 0;
            int c2 = 0;

            for (int start = 0; start + 5 <= s.length(); start++) {
                if (s.startsWith("KUMOH", start)) c1++;
                if (s.startsWith("HOMUK", start)) c2++;
            }

            ans += Math.max(c1, c2);
        }
        System.out.println(ans);

    }
}
