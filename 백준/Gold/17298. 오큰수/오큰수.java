import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        //스택
        Stack<Integer> stack=new Stack<>();
        int N=Integer.parseInt(br.readLine());
        // 스택에 넣을 숫자
        int ar[]=new int[N];
        // 결과 배열
        int result[]=new int[N];
        for(int i=0;i<N;i++){
            result[i]=-1;
        }
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ar[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            int num=ar[i];
            while(!stack.isEmpty()&&ar[stack.peek()]<num){
                result[stack.pop()]=num;
            }
            stack.push(i);

        }
        for(int num:result){
            bw.append(num+" ");
        }
        bw.flush();
        bw.close();

    }

}
