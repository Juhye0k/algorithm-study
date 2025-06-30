import java.io.*;


public class Main {
    static int ans;
    static int ar[];
    public static int solve(int n){
        if(n <= 1)
            return 0;
        int result =Integer.MAX_VALUE;
        if(ar[n] != Integer.MAX_VALUE)
            return ar[n];

        if(n % 3 == 0)
            result = Math.min(result,solve(n/3)+1);
        if(n % 2 == 0)
            result = Math.min(result,solve(n/2)+1);
        ar[n] = Math.min(result,solve(n-1)+1);
        return ar[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;
        int N = Integer.parseInt(br.readLine());
        ar = new int[N+1];
        for(int i = 0; i <= N; i++){
            ar[i] = Integer.MAX_VALUE;
        }
        System.out.println(solve(N));
    }
}