import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      int N=Integer.parseInt(br.readLine());
      StringTokenizer st;
      int rank[]=new int[N];
      int people[][]=new int[N][N];

      for(int i=0;i<N;i++)
      {
          rank[i]=1;
      }
      for(int i=0;i<N;i++)
      {
          st=new StringTokenizer(br.readLine());
          people[i][0]=Integer.parseInt(st.nextToken());
          people[i][1]=Integer.parseInt(st.nextToken());
      }
      for(int i=0;i<N;i++)
      {
          for(int j=0;j<N;j++)
          {
              if(i==j)
                  continue;

              if(people[i][0]<people[j][0]&&people[i][1]<people[j][1])
                  rank[i]++;
          }
      }
        for (int i : rank) {
            System.out.print(i+" ");
        }



    }
}

