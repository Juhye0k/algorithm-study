import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int weight=sc.nextInt();

        int box3 = 0,box5=0;
        int result=Integer.MAX_VALUE;
        for(int i=0;i<=weight/3;i++)
        {
            int temp=weight-3*i;
            if(temp%5==0)
            {
                box5=temp/5;
                box3=i;
                if(result>box5+box3)
                    result=box5+box3;
            }
        }
        if (result == Integer.MAX_VALUE) {
            result = -1; // 가능한 조합이 없을 경우
        }
        System.out.println(result);

    }
}