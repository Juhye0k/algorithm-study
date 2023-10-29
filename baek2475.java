

import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		for(int i=0;i<5;i++)
		{
			int num=sc.nextInt();
			sum+=num*num;
		}
		System.out.print(sum%10);
		
	}
	
}
	
	
	
/*        검증수= 고유번호 5자리 각각의 숫자를 제곱한 수의 합을 10으로 나눈 나머지
 *        
 *        고유번호가 5자리 이므로 for 반복문을 5번 돌려서 입력받은 다음, sum 변수에 더해준다.
 *        더해준 sum 변수를 10으로 나눈 나머지를 계산하여 출력하면 되는 간단한 문제임
 * 
 */


