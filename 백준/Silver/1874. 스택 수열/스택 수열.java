import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       int n=Integer.parseInt(br.readLine());
       int ar[]=new int[n];
       boolean result=true;
       for(int i=0;i<n;i++)
       {
           ar[i]=Integer.parseInt(br.readLine());
       }
       Deque<Integer> deque=new ArrayDeque<>();
       int k=0;
       int stackNum=1;
       StringBuffer bf=new StringBuffer();
       while(k!=n)
       {
           if(stackNum<=ar[k])
           {
               deque.addFirst(stackNum);
               stackNum++;
               bf.append("+\n");

           }
           else if(stackNum>ar[k])
           {
              int getStackumber=deque.pollFirst();
              if(getStackumber!=ar[k])
              {
                  System.out.println("NO");
                  result=false;
                  break;
              }


              else
              {
                  ar[k]=getStackumber;
                  k++;
                  bf.append("-\n");

              }
           }
       }
       if(result)
       {
           System.out.println(bf.toString());
       }








    }
}