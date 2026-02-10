import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        StringTokenizer st;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            
            switch (command) {
                case 1: 
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                    
                case 2: 
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                    
                case 3: 
                    if (deque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(deque.pollFirst()).append("\n");
                    }
                    break;
                    
                case 4: 
                    if (deque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(deque.pollLast()).append("\n");
                    }
                    break;
                    
                case 5: 
                    sb.append(deque.size()).append("\n");
                    break;
                    
                case 6: 
                    sb.append(deque.isEmpty() ? "1\n" : "0\n");
                    break;
                
                case 7:
                    if (deque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(deque.peekFirst()).append("\n");
                    }
                    break;
                    
                case 8:
                    if (deque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(deque.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}