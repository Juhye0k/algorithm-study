import java.io.*;

public class Main {
    static int ar[];
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 계단의 개수
        int n=Integer.parseInt(br.readLine());
        // 한 줄에 하나씩 제일 아래놓인 계딴부터 순서대로 각 계단에 쓰인 점수
        ar=new int[n+1];
        dp=new int[n+1];
        for(int i=1;i<=n;i++){
            int num=Integer.parseInt(br.readLine());
            ar[i]=num;
        }
        
        dp[1]=ar[1];
        if(n==1){
            System.out.println(dp[1]);
            return;
        }
        dp[2]=ar[1]+ar[2];
        if(n==2){
            System.out.println(dp[2]);
            return;
        }
        for(int i=3;i<=n;i++){
            dp[i]=Math.max(dp[i-3]+ar[i-1]+ar[i],dp[i-2]+ar[i]);
        }
        System.out.println(dp[n]);
    }
}
