import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;




public class Main {
    public static String reverse(String word){
        return new StringBuilder(word).reverse().toString();
    }
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       int T=Integer.parseInt(br.readLine());
       for(int i=0;i<T;i++)
       {
           String str[]=br.readLine().split(" ");
           for(int j=0;j<str.length;j++)
           {
               System.out.print(reverse(str[j])+" ");
           }
           System.out.println();
       }


    }
}