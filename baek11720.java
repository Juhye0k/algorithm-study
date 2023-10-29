package firs;

import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		int n=sc.nextInt();
		sc.nextLine();
		String s=sc.nextLine();
		for(int i=0;i<n;i++)
		{
			
			int m=s.charAt(i)-'0';
			sum+=m;
		}
		System.out.print(sum);
		
	}
	
}
	
	
	
/*    막힌 부분:띄어쓰기 없이 입력받는 숫자의 합을 어떻게 구해야하나?
 * 1. 문자열로 입력 받아서 숫자로 변환을 해보자
 * 2. 문자열을 입력 받고 각각의 문자를 숫자로 변경해야 합을 구할 수 있음
 * 3. 반복문과 charAt메소드를 이용하여 각 문자를 받는다.
 * 4. ****char형은 int로 강제 형변환이 불가능하기 때문에, 아스키 코드의 아이디어를 빌려서 해결함 ***
 * 5. 숫자 0~9의 아스키 코드는 48~57인데, 문자형태의 각 숫자에서 0을 빼면 본래의 숫자가 나옴
 * 
 * 
 */


