
import java.util.Scanner;




class Main {
	
	public static int factorial(int num) {
		if(num>1)
			return num*factorial(num-1);
		else
			return 1;
		
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		System.out.print(factorial(num));
		
		
	}
	
}
	
	
	

	


