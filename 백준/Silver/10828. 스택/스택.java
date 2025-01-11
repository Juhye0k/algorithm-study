import java.io.*;
import java.util.*;

import static java.sql.Types.NULL;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Stack<Integer> stack=new Stack<>();
        int N=Integer.parseInt(br.readLine());

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            String s1=st.nextToken();
            if(s1.equals("push")){
                int num=Integer.parseInt(st.nextToken());
                stack.push(num);
            }
            else if(s1.equals("pop")){
                if(stack.isEmpty())
                {
                    bw.write(-1+"\n");
                    continue;
                }
                int num=stack.pop();
                bw.write(num+"\n");
            }
            else if(s1.equals("size")){
                bw.write(stack.size()+"\n");
            }
            else if(s1.equals("empty")){
                if(stack.isEmpty()){
                    bw.write(1+"\n");
                }
                else
                    bw.write(0+"\n");
            }
            else{
                if(stack.isEmpty())
                {
                    bw.write(-1+"\n");
                    continue;
                }
                int num=stack.peek();
                bw.write(num+"\n");
            }
        }
        bw.flush();
        bw.close();

    }


}
