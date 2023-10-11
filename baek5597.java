package firs;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

class Main {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		List<Integer> arr=new LinkedList<Integer>();
		for(int i=0;i<30;i++)
			arr.add(i+1);
		
		for(int i=0;i<28;i++) {
			int attend=sc.nextInt();
			arr.remove(Integer.valueOf(attend));
		}
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
				
	}
}
// 1. 배열에 1~30까지 채운다음, 출석자의 번호를 입력받는다. 입력받을 때마다 배열에서 삭제
// 2. 삭제하는 과정에서, 어떤 메소드를 사용해야할지 몰랐음. (LinkedList 컬렉션 프레임워크 사용을 생각못함)
// 특정 값을 삭제하는 메소드를 몰라서 막혔음. 처음에는 그냥 인덱스를 삭제해줬는데 삭제하는 과정에서 크기가 줄어드니 크기가 줄어들어 예외가 발생하였음