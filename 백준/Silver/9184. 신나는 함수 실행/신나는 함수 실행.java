import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int ar[][][];

    public static int w(int a, int b, int c){
        if(a < -50 || b < -50 || c < -50 || a > 50 || b > 50 || c > 50)
            return 0;

        if (ar[a+50][b+50][c+50] != -1)
            return ar[a+50][b+50][c+50];

        int result = 0;

        if (a <= 0 || b <= 0 || c <= 0){
            ar[a+50][b+50][c+50] = 1;
            return 1;
        }
        else if (a > 20 || b > 20 || c > 20)
            result = w(20, 20, 20);
        else if(a < b && b < c)
            result = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        else
            result = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);

        ar[a+50][b+50][c+50] = result;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int a=0, b=0, c=0;
        ar = new int[101][101][101];

        // 3차원 배열을 -1로 초기화
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 101; j++){
                for(int k = 0; k < 101; k++){
                    ar[i][j][k] = -1;
                }
            }
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1)
                break;

            bw.write("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c) + "\n");
        }
        bw.flush();
        bw.close();
    }
}