import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        int runCount = 0;
        
        while (true) {
            int[] b = new int[n]; 
            
            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = i + 1; j < n; j++) {
                    if (a[j] > a[i]) {
                        count++;
                    }
                }
                b[i] = count;
            }
            
            runCount++; 
            
            if (Arrays.equals(a, b)) {
                break;
            }
            
            a = b;
        }
        
        System.out.println(runCount);
    }
}