import java.io.*;
public class Main {
    static int ar[];
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine()); // 포도주 잔의 개수 n
        ar=new int[n+1];
        dp=new int[n+1];
        int result=0;
        for(int i=1;i<=n;i++){
            ar[i]=Integer.parseInt(br.readLine());
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
            dp[i]=Math.max(Math.max(ar[i]+ar[i-1]+dp[i-3],dp[i-2]+ar[i]),dp[i-1]);
        }
        System.out.println(dp[n]);
    }
}
