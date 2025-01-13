import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int sequence;
    static Queue<Integer> queue;
    static Stack<Integer> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        queue=new LinkedList<>();
        stack=new Stack<>();
        sequence=1;
        N=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int a=Integer.parseInt(st.nextToken());
            queue.add(a);
        }

        while(finish()){
            while(!stack.isEmpty() && stack.peek()==sequence){
                stack.pop();
                sequence++;
            }
            if(!queue.isEmpty())
            {
                if(queue.peek()==sequence)
                {
                    queue.poll();
                    sequence++;
                }
                else{
                    stack.push(queue.poll());
                }
            }
        }
        if(sequence==N+1)
        {
            bw.write("Nice");
        }
        else
        {
            bw.write("Sad");
        }
        bw.flush();
        bw.close();


    }
    public static boolean finish(){
        if(queue.isEmpty()&&(stack.isEmpty()||stack.peek()!=sequence))
            return false;
        else
            return true;
    }
}
