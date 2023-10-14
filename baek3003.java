import java.util.Scanner;


class Main {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] ar=new int[] {1,1,2,2,2,8};	
		for(int i=0;i<6;i++)
		{
			int num=sc.nextInt();
			ar[i]=ar[i]-num;
		}
		for(int n:ar)
			System.out.print(n+" ");
		
		
	}
}
