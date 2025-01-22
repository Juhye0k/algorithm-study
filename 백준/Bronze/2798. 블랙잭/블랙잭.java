import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st1=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st1.nextToken());
		int m=Integer.parseInt(st1.nextToken());
		int max=0;
		StringTokenizer st2=new StringTokenizer(br.readLine());
		int[] ar=new int[n];
		for(int i=0;i<ar.length;i++)
			ar[i]=Integer.parseInt(st2.nextToken());
		for(int i=0;i<n-2;i++)
			for(int j=i+1;j<n-1;j++)
				for(int k=j+1;k<n;k++)
				{	
					int sum=ar[i]+ar[j]+ar[k];
					if(sum>max && sum<=m)
						max=sum;
				}
		System.out.print(max);
		
		
		
	}
	
}

