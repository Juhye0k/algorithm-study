import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 브루트포스 -> 시간복잡도 10^5 * 10^5 = 10^10 터짐
        int[] count = new int[1000001];
        int[] nums = new int[N];
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            count[num]++;
            nums[i] = num;
        }
        for(int i = 0; i<nums.length; i++) {
            int currentNum = nums[i];
            int hits = 0;
            for(int j = 1; j*j <= currentNum; j++) {
                if(currentNum % j == 0) {
                    hits+=count[j];
                    if (j * j != currentNum) {
                        hits += count[currentNum / j];
                    }
                }
            }
            hits-=1;
            sb.append(hits).append("\n");
        }
        System.out.println(sb);
    }
}
