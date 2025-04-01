import java.io.*;
import java.util.*;

public class Main {
    static String[][] ar;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int R, C;
    static boolean can = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ar = new String[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            String line = br.readLine();
            for (int j = 1; j <= C; j++) {
                ar[i][j] = String.valueOf(line.charAt(j - 1));
            }
        }
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (ar[i][j].equals("W")) {
                    processWolf(i, j);
                }
            }
        }

        if (!can) {
            bw.write("0\n");
        } else {
            bw.write("1\n");
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    bw.write(ar[i][j]);
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }
    
    public static void processWolf(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 1 && nx <= R && ny >= 1 && ny <= C) {
                if (ar[nx][ny].equals("S")) {
                    can = false;
                    return;
                } else if (ar[nx][ny].equals(".")) {
                    ar[nx][ny] = "D";
                }
            }
        }
    }
}
