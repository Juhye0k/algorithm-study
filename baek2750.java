import java.util.Scanner;



class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		int ar[]=new int[test];
		for(int i=0;i<test;i++)
		{
			ar[i]=sc.nextInt();
		}
		for(int i=0;i<ar.length-1;i++)
		{
			for(int j=0;j<ar.length-1;j++)
			{
				if(ar[j]>ar[j+1])
				{
					int temp=ar[j+1];
					ar[j+1]=ar[j];
					ar[j]=temp;
				}
			}
			
		}
		for(int i=0;i<ar.length;i++)
			System.out.println(ar[i]);
		
	}
	
}
	
	
	

	


