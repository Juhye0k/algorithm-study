import java.io.*;
import java.util.StringTokenizer;


public class Main {


    static int ar[][];
    static int max[][];
    static int min[][];
    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ar = new int[N+1][4];
        max = new int[N+1][4];
        min = new int[N+1][4];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<=N; i++)
            for(int j=1; j<=3; j++){
                max[i][j] = -1;
                min[i][j] = Integer.MAX_VALUE;
            }
        max[1][1] = ar[1][1];
        min[1][1] = ar[1][1];
        max[1][2] = ar[1][2];
        min[1][2] = ar[1][2];
        max[1][3] = ar[1][3];
        min[1][3] = ar[1][3];
        for(int i=2; i<=N; i++){
            for(int j=1; j<=3; j++){
                // j가 1일 때
                if(j==1){
                    max[i][j] = Math.max(max[i-1][j]+ar[i][j],max[i-1][j+1]+ar[i][j]);
                    min[i][j] = Math.min(min[i-1][j]+ar[i][j],min[i-1][j+1]+ar[i][j]);
                }
                // j가 2일 때
                if(j==2){
                    max[i][j] = Math.max(max[i-1][j-1]+ar[i][j],Math.max(max[i-1][j]+ar[i][j],max[i-1][j+1]+ar[i][j]));
                    min[i][j] = Math.min(min[i-1][j-1]+ar[i][j],Math.min(min[i-1][j]+ar[i][j],min[i-1][j+1]+ar[i][j]));
                }
                // j가 3일 때
                if(j==3){
                    max[i][j] = Math.max(max[i-1][j-1]+ar[i][j],max[i-1][j]+ar[i][j]);
                    min[i][j] = Math.min(min[i-1][j-1]+ar[i][j],min[i-1][j]+ar[i][j]);
                }
            }
        }
        int maxResult = Math.max(max[N][1], Math.max(max[N][2], max[N][3]));
        int minResult = Math.min(min[N][1], Math.min(min[N][2], min[N][3]));

        System.out.println(maxResult + " " + minResult);


    }
}