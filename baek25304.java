
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		int value,count;
		int result=0;
		Scanner sc=new Scanner(System.in);
		value=sc.nextInt();
		count=sc.nextInt();
		
		for(int i=0;i<count; i++) {
			int won,many;
			won=sc.nextInt();
			many=sc.nextInt();
			result+=(won*many);
			
		}
		
		
		if(value==result)
			System.out.println("Yes");
		else
			System.out.println("No");
		
	}
		
		
	 
		
		
		
}


