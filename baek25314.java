

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		int num;
		
		Scanner sc=new Scanner(System.in);
		
		num=sc.nextInt();
		
		int count=num/4;
		String arr="int";
		for(int i=0;i<count;i++) {
			arr="long "+arr;
		}
		
		System.out.println(arr);
	 
		
		

	}


}