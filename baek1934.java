
import java.util.Scanner;



class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		for(int i=0;i<test;i++)
		{
			int n1=sc.nextInt();
			int n2=sc.nextInt();
			int min=0;
			for(int j=1;j<=n1;j++)
			{
				if(n1%j==0&&n2%j==0)
					min=j;
			}
			n1=n1/min;
			n2=n2/min;
			System.out.println(min*n1*n2);
			
		}
		
	}
	
}
	
	
	

	


