import java.io.*;

public class Main {
    static int count1=0,count2=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // n이 주어짐
        int n = Integer.parseInt(br.readLine());
        fib(n);
        fibonacci(n);
        bw.write(count1+" "+count2);
        bw.flush();
        bw.close();
        br.close();

    }
    public static int fib(int n){
        if(n == 1 || n == 2)
        {
            count1++;
            return 1;
        }

        else
            return fib(n-1)+fib(n-2);
    }
    public static int fibonacci(int n){
        int f[]=new int[n+1];
        f[1] = 1;
        f[2] = 1;
        for(int i=3;i<=n;i++){
            f[i] = f[i-1]+f[i-2];
            count2++;
        }
        return f[n];
    }
}