package firs;
import java.util.Scanner;

import java.util.Arrays;



class Main {
	public static char[] reverse(char[] ar) {
		char[] list=new char[ar.length];
		for(int i=0;i<ar.length;i++) {
			list[i]=ar[ar.length-(i+1)];
		}
		
		return list;
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String word=sc.nextLine();
		char[] wordlist=word.toCharArray();
		
		if(Arrays.equals(wordlist,reverse(wordlist)))
			System.out.println(1);
		else
			System.out.println(0);
		
		
	}
}
