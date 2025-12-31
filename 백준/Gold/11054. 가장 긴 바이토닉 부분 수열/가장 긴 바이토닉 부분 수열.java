
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] ar = new int[1000];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int [] dpFront = new int[1000];
        int [] dpBack = new int[1000];
        Arrays.fill(dpFront, 1);
        Arrays.fill(dpBack, 1);

        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++){
                if(ar[j] < ar[i] && dpFront[j]+1>dpFront[i]) {
                    dpFront[i] = dpFront[j]+1;
                }
            }
        }
        for(int i=N-1; i>=0; i--) {
            for(int j=N-1; j>i; j--){
                if(ar[j] < ar[i] && dpBack[j]+1>dpBack[i]) {
                    dpBack[i] = dpBack[j]+1;
                }
            }
        }
        int result = 0;
        for(int i=0; i<N; i++) {
            result = Math.max(result, dpFront[i]+dpBack[i]-1);
        }
        System.out.println(result);


    }

}