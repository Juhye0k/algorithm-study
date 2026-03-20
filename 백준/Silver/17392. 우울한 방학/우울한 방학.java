import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int happyDays = 0;
        
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int h = Integer.parseInt(st.nextToken());
                happyDays += (h + 1); 
            }
        }
        
        int sadDays = M - happyDays;
        
        if (sadDays <= 0) {
            System.out.println(0);
            return;
        }
        
        int sections = N + 1;
        
        int q = sadDays / sections;
        int r = sadDays % sections;
        
        long totalSadness = 0;
        
        totalSadness += (long) r * getSadness(q + 1);
        
        totalSadness += (long) (sections - r) * getSadness(q);
        
        System.out.println(totalSadness);
    }
    
    private static long getSadness(long c) {
        return c * (c + 1) * (2 * c + 1) / 6;
    }
}