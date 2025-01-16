import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //스택 정의
        Stack<Integer> stack = new Stack<>();
        // 명령의 수 N을 입력받는다.
        int N=Integer.parseInt(br.readLine());
        // N개의 크기만큼 명령을 받는다.
        for(int i=0;i<N;i++){
            // 명령 입력받기
            String ar[]=br.readLine().split(" ");
            // 명령 추출
            String command=ar[0];
            // 1일때
            if(command.equals("1")){
                stack.push(Integer.parseInt(ar[1]));
            }
            // 2일때
            else if(command.equals("2")){
                if (!stack.isEmpty()) {
                    bw.append(stack.pop() + "\n");
                } else {
                    bw.append("-1"+"\n");
                }
            }
            // 3일때
            else if(command.equals("3")){
                bw.append(stack.size()+"\n");
            }
            // 4일때
            else if(command.equals("4")){
                if(stack.isEmpty()){
                    bw.append("1"+"\n");
                }
                else{
                    bw.append("0"+"\n");
                }
            }
            // 5일때
            else if(command.equals("5")){
                if(!stack.isEmpty()){
                    bw.append(stack.peek()+"\n");
                }
                else {
                    bw.append("-1"+"\n");
                }
            }
        }
        bw.flush();
        bw.close();

    }

}
