package firs;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

class Main {
	
	public static int max(int[] list)
	{
		int max=list[0];
		for(int i=1;i<list.length;i++)
			if(list[i]>max)
				max=list[i];
				
		return max;
	}
	public static int min(int[] list)
	{
		int min=list[0];
		for(int i=1;i<list.length;i++)
			if(list[i]<min)
				min=list[i];
		return min;
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		int[] ar=new int[num];
		for(int i=0;i<num;i++) {
			ar[i]=sc.nextInt();
		}
		int minNum=min(ar);
		int maxNum=max(ar);
		System.out.println(minNum);
		System.out.println(maxNum);
		
	}
}
