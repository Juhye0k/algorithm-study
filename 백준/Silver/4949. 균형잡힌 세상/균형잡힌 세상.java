import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;
            String str = br.readLine();

            if (str.equals("."))  
                break;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '(' || ch == '[') {
                    stack.push(ch);  
                } else if (ch == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') { 
                        isValid = false;
                        break;
                    }
                    stack.pop();  
                } else if (ch == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') { 
                        isValid = false;
                        break;
                    }
                    stack.pop(); 
                }
            }

            if (stack.isEmpty() && isValid) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
