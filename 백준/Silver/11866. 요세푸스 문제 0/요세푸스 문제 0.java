import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer(br.readLine());

      int N=Integer.parseInt(st.nextToken());
      int K=Integer.parseInt(st.nextToken());
      int index=K-1;
      List<Integer> list=new ArrayList<>();
      for(int i=0;i<N;i++)
        list.add(i+1);
      System.out.print("<");
      while(!list.isEmpty())
      {
        System.out.print(list.get(index));
        if(list.size()!=1)
          System.out.print(", ");
        list.remove(index);
        index=index-1;
        if(list.isEmpty())
          break;
        index=(index+K)% (list.size());
      }
      System.out.println(">");



    }
}

