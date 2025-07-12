import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int xpos,ypos;
		
		Scanner sc=new Scanner(System.in);
		xpos=sc.nextInt();
		ypos=sc.nextInt();
		
		
		if(xpos>0 && ypos>0)
			System.out.println("1");
		if(xpos<0 && ypos>0)
			System.out.println("2");
		if(xpos<0 && ypos<0)
			System.out.println("3");
		if(xpos>0 && ypos<0)
			System.out.println("4");

		
		
		
	}

}
