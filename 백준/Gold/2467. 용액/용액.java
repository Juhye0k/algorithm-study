import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int leftIndex = 0;
        int rightIndex = N-1;
        int leftAnswer = 0, rightAnswer = 0;
        int answer = Integer.MAX_VALUE;
        while(leftIndex < rightIndex) {
            int temp = ar[leftIndex] + ar[rightIndex];
            if(answer > Math.abs(temp)) {
                answer = Math.abs(temp);
                leftAnswer = ar[leftIndex];
                rightAnswer = ar[rightIndex];
            }
            if (temp > 0) {
                rightIndex--;
            }
            else if (temp <0) {
                leftIndex++;
            }
            else {
                leftAnswer = ar[leftIndex];
                rightAnswer = ar[rightIndex];
                sb.append(leftAnswer + " " + rightAnswer);
                System.out.println(sb);
                return;
            }
        }
        sb.append(leftAnswer + " " + rightAnswer);
        System.out.println(sb);
    }
}
