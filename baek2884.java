
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int time,minute;
		
		Scanner sc=new Scanner(System.in);
		time=sc.nextInt();
		minute=sc.nextInt();
		
		
		int realMinute=minute-45;
		
		if(realMinute<0)
		{	
			realMinute=60+realMinute;
			time-=1;
			if(time<0)
				time=24+time;
		}
		System.out.printf("%d %d",time,realMinute);
		
		

		
		
		
	}

}
