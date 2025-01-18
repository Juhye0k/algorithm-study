import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack=new Stack<>();
        // t를 입력
        int t=Integer.parseInt(br.readLine());
        // t 반복문
        for(int i=0;i<t;i++){
            // 문자열 길이 입력
            int n = Integer.parseInt(br.readLine());
            // 문자열 S 입력
            String S=br.readLine();
            for(int j=0;j<S.length();j++) {
                char c=S.charAt(j);
                stack.push(c);
                recursive(stack);
            }
            for(char c:stack) {
                bw.append(c);
            }
            bw.append("\n");
            stack.clear();
        }

        bw.flush();
        bw.close();
    }
    public static void recursive(Stack<Character> stack){
        if(stack.size()>=3) {
            if(stack.get(stack.size()-3)=='A' && stack.get(stack.size()-2)=='B' && stack.get(stack.size()-1)=='B') {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push('B');
                recursive(stack);
                stack.push('A');
            }
            else{
                return;
            }
        }
    }

}
