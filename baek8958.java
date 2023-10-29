package firs;

import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int count=sc.nextInt();
		sc.nextLine();
	
		for(int i=0;i<count;i++)
		{
			int sum=0;
			int plusNumber=0;
			String answer=sc.nextLine();
			for(int j=0;j<answer.length();j++)
			{
				
				if(answer.charAt(j)=='O')
				{
					plusNumber++;
					sum+=plusNumber;
					
				}
				else
				{
					plusNumber=0;
					
				}
				
			}
			System.out.println(sum);
		}
	}
	
}
	
	
	
/*        첫째 줄에 테스트 케이스가 주어지니 Scanner로 입력을 받은 다음, 정수형을 정의하여 넣어준다.
 *        문제를 맞았을 때, 연속된 O만큼 점수가 늘어난다. 
 *        ex) OOXOO = 1+2+0=1+2    OXOXOX=1+0+1+0+1+0   OOXXOXXO= 1+2+0+0+1+0+0+1
 *        
 *        문제가 틀렸을 때만 더해주는 숫자를 0으로 초기화해주고, 일반적인 경우에는 더해지는 점수를 1씩 추가해주면 되겠다.
 *        
 */


