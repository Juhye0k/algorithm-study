
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minQueue= new PriorityQueue<>(1,(e1,e2)->
        {
            return e2-e1;
        });
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if(minQueue.isEmpty())
                    System.out.println(0);
                else
                {
                    System.out.println(minQueue.poll());
                }

            } else {
               minQueue.add(n);
            }
        }

    }
}