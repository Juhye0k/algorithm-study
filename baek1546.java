

import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int subCount;
		float maxSub=0;
		float result=0;
		subCount=sc.nextInt();
		float ar[]=new float[subCount];
		for(int i=0;i<subCount;i++) {
			ar[i]=sc.nextInt();
			if(ar[i]>maxSub)
				maxSub=ar[i];
		}
		for(int i=0;i<subCount;i++)
		{
			ar[i]=ar[i]/maxSub*100;
		}
		for(float num:ar)
		{
			result+=num;
		}
		
		System.out.println(result/subCount);
		
		
	
		
		
	
	}
}
//처음에 int형으로 선언하여 오류가 발생함, 이후 float형으로 고쳐서 해결함