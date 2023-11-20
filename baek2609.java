package firs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		int g=1;
		if(a>b)
		{
			for(int i=1;i<=b;i++)
			{
				if(a%i==0&&b%i==0)
					g=i;
			}
		}
		else
		{
			for(int i=1;i<=a;i++)
			{
				if(a%i==0&&b%i==0)
					g=i;
			}
		}
		System.out.println(g);
		int l=g*(b/g)*(a/g);
		System.out.print(l);
		
		
		
		
	}
	
}

