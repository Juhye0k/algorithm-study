
import java.util.Scanner;

import java.util.Arrays;


class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int []ar=new int[5];
		ar[0]=sc.nextInt();
		ar[1]=sc.nextInt();
		ar[2]=sc.nextInt();
		ar[3]=sc.nextInt();
		ar[4]=sc.nextInt();
		Arrays.sort(ar);
		int average=(ar[0]+ar[1]+ar[2]+ar[3]+ar[4])/5;
		int middle=ar[2];
		
		System.out.println(average);
		System.out.println(middle);
		
	}
	
}
	
	
	

	


