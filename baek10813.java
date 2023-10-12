package firs;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

class Main {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt(); //바구니의 개수
		int m=sc.nextInt();  // 공을 바꾸는 횟수
		int ar[]=new int[n];  //바구니 배열 정의
		for(int i=0;i<n;i++)    //바구니에 공 넣기(i+1인 이유는 배열은 인덱스 0부터 시작)
			ar[i]=i+1;
			
		for(int i=0;i<m;i++)
		{
			int temp=0;   //임시 공간 
			int b1=sc.nextInt()-1;
			int b2=sc.nextInt()-1;
			temp=ar[b2];
			ar[b2]=ar[b1];
			ar[b1]=temp;
			
		}
		for(int k:ar)
			System.out.print(k+" ");
				
	}
}
