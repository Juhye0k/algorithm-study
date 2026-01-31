import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] origin = new int[N]; 
        int[] sorted = new int[N]; 
        
        for(int i = 0; i < N; i++) {
            sorted[i] = origin[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(sorted);
        
       
        HashMap<Integer, Integer> rankingMap = new HashMap<>();
        int rank = 0;
        
        for(int v : sorted) {
            if(!rankingMap.containsKey(v)) {
                rankingMap.put(v, rank);
                rank++; 
            }
        }
        
        for(int key : origin) {
            sb.append(rankingMap.get(key)).append(' ');
        }
        
        System.out.println(sb);
    }
}