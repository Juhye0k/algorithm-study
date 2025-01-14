#include <stdio.h>
// 1. 숫자 3개를 입력받는다
// 2. 숫자 3개의 곱 결과를 도출한다.
// 3. 연산 결과를 배열에 담아내야한다
int main(void)
{
	int arr[10]={0,};
	int num1,num2,num3,result;
	scanf("%d",&num1);
	scanf("%d",&num2);
	scanf("%d",&num3);
	
	result=num1*num2*num3;
	
	for(int i=result ;i>0;i/=10)
	{
		++arr[i%10];
	}
	for(int i=0;i<10;i++)
		printf("%d\n",arr[i]);
	return 0;

	
}

