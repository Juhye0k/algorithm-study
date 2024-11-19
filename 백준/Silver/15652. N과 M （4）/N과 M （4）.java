import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int M;
    public static int ar[];

    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st=new StringTokenizer(br.readLine());
       N=Integer.parseInt(st.nextToken());
       M=Integer.parseInt(st.nextToken());
       ar=new int[N];
       DFS(0);
       System.out.println(sb);



    }
    public static void DFS(int depth) {
        if(depth==M){
            for(int i=0;i<M;i++)
            {
                sb.append(ar[i]+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=(depth==0?1:ar[depth-1]);i<=N;i++)
        {

                ar[depth]=i;
                DFS(depth+1);

        }
    }

}
