
import java.math.BigInteger;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        BigInteger number=BigInteger.ONE;
        int result=0;
        for(int i=N;i>0;i--)
        {
            number=number.multiply(BigInteger.valueOf(i));
        }
        String s=number.toString();
        int index=s.length()-1;
        while(s.charAt(index)==48)
        {
            result=result+1;
            index=index-1;
        }
        System.out.println(result);
    }
}
