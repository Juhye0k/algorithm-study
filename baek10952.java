import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int i=1,j=1;
		
		while(i!=0&&j!=0)
		{
			i=sc.nextInt();
			j=sc.nextInt();
			if(i==0&&j==0)
				break;
			System.out.println(i+j);
			
			
		}
	 
		
		

	}


}