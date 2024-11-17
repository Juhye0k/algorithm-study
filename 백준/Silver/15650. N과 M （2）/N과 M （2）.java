import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int M;
    public static int ar[];



    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        ar=new int[N];

        DFS(1,0);




    }
    public static void DFS(int start, int depth)
    {
        if(depth==M)
        {
            for(int i=0;i<M;i++)
            {
                System.out.print(ar[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i=start;i<=N;i++)
        {
            ar[depth]=i;
            DFS(i+1,depth+1);
        }
    }
}
