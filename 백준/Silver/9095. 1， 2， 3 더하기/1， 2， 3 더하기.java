
import org.w3c.dom.Node;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나


public class Main {

    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        dp = new int[12];
        dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        for(int i=4; i<12; i++){
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }
        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);

    }
}
// 1,2,3으로 더하는 문제 -> N번째 숫자는 N-1에서 계산한 값을 이용할 수 있지 않을까?에서 dp라는 아이디어를 획득
// 1,2,3 .. 해서 dp[N]을 만들 수 있는 아이디어를 찾아보기. 이것은 문제 많이 풀면서 하드웨어를 익혀야할듯