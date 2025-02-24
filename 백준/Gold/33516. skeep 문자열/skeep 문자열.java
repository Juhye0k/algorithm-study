
import java.io.*;
import java.util.*;


public class Main {
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack=new Stack<>();
        result=0;
        int N=Integer.parseInt(br.readLine());
        String str=br.readLine();
        for(int i=0;i<N;i++){
            char c=str.charAt(i);
            stack.push(c);
            if(stack.size()>=5){
                recursive(stack);
                }
            }
        System.out.println(result);
    }
    public static void recursive(Stack<Character> stack){
        if (stack.size() >= 5
                && stack.get(stack.size() - 5) == 's'
                && stack.get(stack.size() - 4) == 'k'
                && stack.get(stack.size() - 3) == 'e'
                && stack.get(stack.size() - 2) == 'e'
                && stack.get(stack.size() - 1) == 'p') {
            // 원하는 로직
            result++;
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
            if(!stack.isEmpty()){
                if(stack.peek().equals('s')){
                    stack.push('k');
                }
                else if(stack.peek().equals('k')){
                    stack.push('e');
                }
                else if(stack.size()>=2&&stack.get(stack.size()-2).equals('k')&&stack.get(stack.size()-1).equals('e')){
                    stack.push('e');
                }
                else if(stack.size()>=2&&stack.get(stack.size()-2).equals('e')&&stack.get(stack.size()-1).equals('e')){
                    stack.push('p');
                }
                else if(stack.peek().equals('p')){
                    stack.push('k');
                }
            }
            recursive(stack);
        }
    }
}
