
import java.util.Arrays;
import java.util.Scanner;



class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int ar[]=new int[4];
		int x=sc.nextInt();
		int y=sc.nextInt();
		int w=sc.nextInt();
		int h=sc.nextInt();
		ar[0]=x;
		ar[1]=y;
		ar[2]=w-x;
		ar[3]=h-y;
		Arrays.sort(ar);
		System.out.print(ar[0]);
		
		
		
	
		
		
		
	}
	
}
	
	
	

	


