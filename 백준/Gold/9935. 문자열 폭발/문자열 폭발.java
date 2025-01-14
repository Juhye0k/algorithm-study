import java.io.*;
import java.util.*;
public class Main {
    static int count;
    static Stack<Integer> stack;
    static List<Integer> list;
    static int i;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack=new Stack<>();

        String str=br.readLine();
        String bomb=br.readLine();
        for(int i=0;i<str.length();i++){
            boolean isBomb=true;
            stack.push(str.charAt(i));
            if(stack.size()>=bomb.length())
            {
                for(int j=0;j<bomb.length();j++)
                {
                    if(stack.get(stack.size()+j-bomb.length())!=bomb.charAt(j))
                    {
                        isBomb=false;
                        break;
                    }
                }
                if(isBomb){
                    for(int j=0;j<bomb.length();j++){
                        stack.pop();
                    }
                }
            }


        }
        if(stack.isEmpty())
            bw.append("FRULA");
        else {
            for(char c:stack)
                bw.append(c);
        }
        bw.flush();
        bw.close();
    }
}
