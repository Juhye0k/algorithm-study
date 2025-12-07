
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;



public class Main {

    static int w,h;
    static final int mod =1000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 종류를 나타내는 정수 T
        int T = Integer.parseInt(br.readLine());

        if(T==1){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A =  Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            long result = A+B;
            char[] str = new char[13];
            for(int i=12; i>=0; i--) {
                str[i] = (char)('a'+(int)(result%26));
                result = result/26;
            }
            System.out.println(new String(str));
        }
        else if(T==2) {
            String str =  br.readLine();
            long result = 0;
            for(int i=0; i<13; i++) {
                result = result*26+(str.charAt(i)-'a');
            }
            System.out.println(result);
        }

    }

}
