import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      int shirtCount=0;
      int penCount1=0,penCount2=0;
      int N=Integer.parseInt(br.readLine());
      int shirt[]=new int[6];
      st=new StringTokenizer(br.readLine());
      for(int i=0;i<6;i++)
        shirt[i]=Integer.parseInt(st.nextToken());

      st=new StringTokenizer(br.readLine());
      int T=Integer.parseInt(st.nextToken());
      int P=Integer.parseInt(st.nextToken());

      for(int i=0;i<6;i++)
      {
        if(shirt[i]%T==0)
          shirtCount+=shirt[i]/T;
        else
          shirtCount+=shirt[i]/T+1;

      }

      penCount1=N/P;
      penCount2=N%P;
      System.out.println(shirtCount);
      System.out.println(penCount1+" "+penCount2);


    }
}

