import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        
        int sum = 0;
        
        for(int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = val;
            sum += val;
        }
        
        
        System.out.println(Math.round((double)sum / N));
        
        Arrays.sort(arr);
        System.out.println(arr[N / 2]);
        
        
        int[] count = new int[8001];
        int maxFreq = 0; 
        
        for(int i = 0; i < N; i++) {
            count[arr[i] + 4000]++; 
            
            if(count[arr[i] + 4000] > maxFreq) {
                maxFreq = count[arr[i] + 4000];
            }
        }
        
        int mode = 10000;   
        boolean flag = false;
        
        for(int i = 0; i <= 8000; i++) {
            if(count[i] == maxFreq) {
                if(flag) { 
                    mode = i - 4000;
                    break; 
                }
                
                mode = i - 4000;
                flag = true;
            }
        }
        System.out.println(mode);
        
        System.out.println(arr[N - 1] - arr[0]);
    }
}