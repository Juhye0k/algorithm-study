import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        long max=0;
        long min=0;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            long num1=Long.parseLong(st.nextToken());
            long num2=Long.parseLong(st.nextToken());
            long large=Math.max(num1,num2);
            long small=Math.min(num1,num2);
            if(large>max)
                max=large;
            if(small>min)
                min=small;
        }
        System.out.println(max*min);


    }
}
