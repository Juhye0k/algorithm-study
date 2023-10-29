

import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int count=sc.nextInt();
		sc.nextLine();
		for(int i=0;i<count;i++)
		{
			int num=sc.nextInt();
			sc.next();
			String s=sc.nextLine();
			for(int j=0;j<s.length();j++)
			{
				for(int k=0;k<num;k++)
				{
					System.out.print(s.charAt(j));
				}
			}
			System.out.println();
		}
		
	}
	
}
	
// 2023 10 29   nexline, next, nextInt 관련 정리 필요
	
	

	


