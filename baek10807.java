
import java.util.Scanner;

 class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int count;
		count=sc.nextInt();
		int ar[]=new int[count]; 
		for(int i=0;i<count;i++)
		{
			int box;
			box=sc.nextInt();
			ar[i]=box;
		}
		int numberCount=0;
		int findNumber;
		findNumber=sc.nextInt();
		for(int i=0;i<ar.length;i++)
		{
			if(ar[i]==findNumber)
				numberCount++;
		}
		
		System.out.println(numberCount);
		
		
		

	}


}