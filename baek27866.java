
import java.util.Scanner;

 class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str;
		int num;
		
		str=sc.nextLine();
		num=sc.nextInt();
		System.out.println(str.substring(num-1,num));
		
		

	}


}