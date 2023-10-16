import java.util.Scanner;



class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		
		int ar1[][]=new int[n][m];
		int ar2[][]=new int[n][m];
		int ar3[][]=new int[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				ar1[i][j]=sc.nextInt();
			}
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				ar2[i][j]=sc.nextInt();
			}
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				ar3[i][j]=ar1[i][j]+ar2[i][j];
			}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				System.out.print(ar3[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
	
}
	
	
	

	


