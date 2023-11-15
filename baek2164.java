package firs;

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Deque<Integer> deq=new ArrayDeque<>();
		for(int i=0;i<n;i++) {
			deq.offerLast(i+1);
		}
		while(deq.size()!=1) {
			deq.pollFirst();
			int a=deq.peekFirst();
			deq.pollFirst();
			deq.offerLast(a);
		}
		int a=deq.peekFirst();
		System.out.print(a);
		
	}
	
}
/*  덱으로 풀었지만 Queue로도 풀 수 있음
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class Main{	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		Queue<Integer> que=new LinkedList<>();
		int n=sc.nextInt();
		for(int i=1;i<=n;i++)
			que.offer(i);
		while(que.size()>1)
		{
			que.poll();
			que.offer(que.poll());
		}
		System.out.print(que.peek());
			
	}
		
		
}
 * 
 */
