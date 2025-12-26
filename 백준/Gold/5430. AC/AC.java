import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();

            Deque<Integer> deque = new ArrayDeque<>();
            
            if (n > 0) {
                String[] arr = str.substring(1, str.length() - 1).split(",");
                for (String s : arr) {
                    deque.add(Integer.parseInt(s));
                }
            }

            boolean isNormal = true; 
            boolean isError = false;

            for (int j = 0; j < p.length(); j++) {
                char cmd = p.charAt(j);

                if (cmd == 'R') {
                    isNormal = !isNormal; 
                } else {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (isNormal) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }

            if (isError) {
                sb.append("error").append("\n");
            } else {
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(isNormal ? deque.pollFirst() : deque.pollLast());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
                sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}