package firs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int number=666;
		int count=1;
		while(count!=n)
		{
			number++;
			if(String.valueOf(number).contains("666"))
				count++;
		}
		System.out.print(number);
		
		
	}
	
}

