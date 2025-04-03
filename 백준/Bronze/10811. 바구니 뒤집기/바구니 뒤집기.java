import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // N: 바구니의 개수, M: 역순으로 바꿀 횟수
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        int[] baskets = new int[N];
        // 바구니 초기화
        for (int i = 0; i < N; i++) {
            baskets[i] = i + 1;
        }
        
        // M번의 역순 작업 수행
        for (int m = 0; m < M; m++) {
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            
            // start부터 end까지의 바구니를 역순으로 변경
            while (start < end) {
                int temp = baskets[start];
                baskets[start] = baskets[end];
                baskets[end] = temp;
                start++;
                end--;
            }
        }
        
        scanner.close();
        
        // 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.print(baskets[i] + " ");
        }
    }
}
