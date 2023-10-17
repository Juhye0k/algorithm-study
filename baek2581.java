
import java.util.Scanner;




class Main {
	

	public static boolean check(int num) {
		if(num==1)
			return false;
		else
		{
			for(int i=2;i<num;i++) {
				if(num%i==0)
					return false;
			}
			return true;
		}
			
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int n=sc.nextInt();
		int sum=0;
		int min=0;
		for(int i=n;i>=m;i--)
		{
			if(check(i)==true)
			{	
				sum+=i;
				min=i;
			}
		}
		if(sum==0)
			System.out.print(-1);
		else
		{
			System.out.println(sum);
			System.out.print(min);
		}
			
		
		
	}
	
}
	
	
	

	


