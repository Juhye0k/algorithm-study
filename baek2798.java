package firs;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		List<Integer> lst=new ArrayList<>();
		StringTokenizer st1=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st1.nextToken());
		int m=Integer.parseInt(st1.nextToken());
		int max=0;
		StringTokenizer st2=new StringTokenizer(br.readLine());
		int[] ar=new int[n];
		for(int i=0;i<ar.length;i++)
			ar[i]=Integer.parseInt(st2.nextToken());
		for(int i=0;i<ar.length-2;i++)
			for(int j=1;j<ar.length-1;j++)
				for(int k=2;k<ar.length;k++)
				{	
					int sum=ar[i]+ar[j]+ar[k];
					if(sum>max && sum<=m)
						max=sum;
				}
		System.out.print(max);
		
		
		
	}
	
}

