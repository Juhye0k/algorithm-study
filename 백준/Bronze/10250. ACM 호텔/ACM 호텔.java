import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int test=Integer.parseInt(br.readLine());
		for(int i=0;i<test;i++)
		{
			StringTokenizer st=new StringTokenizer(br.readLine());
			int h=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());
			
			int roomNumber;
			if(n%h==0)
				roomNumber=n/h;
			else
				roomNumber=n/h+1;
			int roomFloor;
			String room="";
			if(n%h==0)
				roomFloor=h;
			else
				roomFloor=n%h;
			if(roomNumber<10)
			{
				room=Integer.toString(roomFloor)+'0'+Integer.toString(roomNumber);
			}
			else
				room=Integer.toString(roomFloor)+Integer.toString(roomNumber);
				
			
			System.out.println(room);
		}
		
	}
}