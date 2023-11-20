package firs;

import java.util.Scanner;


public class Main {

	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in); // 숫자 입력 
		int n=sc.nextInt();
		int ar[]=new int[n];
		for(int i=0;i<n;i++)
			ar[i]=i+1;
		int count=1;            // 투 포인터 이용하기 위해서 start,end index 선언
		int sum=1;
		int start_index=1;
		int end_index=1;
		while(end_index!=n)
		{
			if(sum==n)
			{
				count++;
				end_index++;
				sum=sum+end_index;
				
			}
			else if(sum<n)
			{
				end_index++;
				sum=sum+end_index;
			}
			else if(sum>n)
			{
				
				sum=sum-start_index;
				start_index++;
			}
		}
		System.out.print(count);
	}
	
}

