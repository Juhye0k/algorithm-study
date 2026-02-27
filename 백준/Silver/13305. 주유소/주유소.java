import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); 

        long[] distances = new long[N - 1]; 
        for (int i = 0; i < N - 1; i++) {
            distances[i] = sc.nextLong();
        }

        long[] prices = new long[N]; 
        for (int i = 0; i < N; i++) {
            prices[i] = sc.nextLong();
        }

        long totalCost = 0;
        long minPrice = prices[0]; 

        for (int i = 0; i < N - 1; i++) {
           
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

          
            totalCost += (minPrice * distances[i]);
        }

        System.out.println(totalCost);
        sc.close();
    }
}