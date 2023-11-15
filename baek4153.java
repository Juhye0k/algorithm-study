package firs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Boolean test;
		while(test=true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			if(a==0&&b==0&&c==0)
			{
				break;
			}
				
			if(a>b&&a>c)
			{
				if(a*a==b*b+c*c)
					System.out.println("right");
				else
					System.out.println("wrong");
			}
			else if(b>c&&b>a)
			{
				if(b*b==a*a+c*c)
					System.out.println("right");
				else
					System.out.println("wrong");
			}
			else if(c>b&&c>a)
			{
				if(c*c==a*a+b*b)
					System.out.println("right");
				else
					System.out.println("wrong");
			}
		}
		
			
	}
	
}