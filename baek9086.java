
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int testCount;
		testCount=sc.nextInt();
		sc.nextLine();
		for(int i=0;i<testCount;i++)
		{
			String str;
			str=sc.nextLine();
			for(int j=0;j<str.length();j++)
			{
				if(j==0||j==str.length()-1)
					System.out.print(str.charAt(j));
				if(str.length()==1)
					System.out.print(str.charAt(j));
			}
			System.out.println();
		}
		
		
		
	
	}
}