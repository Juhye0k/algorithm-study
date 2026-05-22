import java.io.*;
public class Main {
    static int[] ar;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ar = new int[N+1];
        for(int i=0; i<ar.length; i++)
            ar[i] = -1;
        int answer = fibonachi(N);
        System.out.println(answer);

    }
    public static int fibonachi(int n) {
        if(ar[n]!=-1)
            return ar[n];
        if(n<=2)
            ar[n] = 1;
        else
            ar[n] = fibonachi(n-1) + fibonachi(n-2);
        return ar[n];
    }
}