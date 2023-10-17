package firs;
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
		int num=sc.nextInt();
		int ar[]=new int[num];
		int count=0;
		for(int i=0;i<ar.length;i++)
			ar[i]=sc.nextInt();
		for(int n:ar)
		{
			if(check(n)==true)
				count+=1;

		}
		System.out.print(count);
			
		
		
	}
	
}
	
	
	

	


