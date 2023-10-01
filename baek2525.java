

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int finishTime,minute,makeTime;
		
		Scanner sc=new Scanner(System.in);
		finishTime=sc.nextInt();
		minute=sc.nextInt();
		makeTime=sc.nextInt();
		
		
		int finishMinute=minute+makeTime;
		
		if(finishMinute>=60) {
			finishTime=finishTime+(finishMinute/60);
			finishMinute=finishMinute%60;
		}
		if(finishTime>24)
			finishTime=finishTime-24;
		System.out.printf("%d %d",finishTime,finishMinute);
		
		

		
		
		
	}

}
