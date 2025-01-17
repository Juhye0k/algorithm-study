import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 빌딩의 크기 N 입력
        int N=Integer.parseInt(br.readLine());
        // 왼쪽에서 오른쪽으로 탐색하기 위해, 스택을 정의
        Stack<Integer> stack=new Stack<>();
        // 스택에 삽입할 배열을 정의
        int ar[]=new int[N];
        // result 변수 정의
        long result=0;
        // 빌딩의 개수 N만큼 빌딩의 높이 입력
        for(int i=0;i<N;i++){
            ar[i]=Integer.parseInt(br.readLine());
        }
        // 왼쪽에서 오른쪽으로 스택에 쌓아가면서 탐색
        for(int i=0;i<N;i++){
            int num=ar[i];
            while(!stack.isEmpty() && ar[stack.peek()] <=num){
                result+=i-stack.pop()-1;
            }
            stack.push(i);
        }
        int last=stack.pop();
        while(!stack.isEmpty()){
            result+=last-stack.pop();
        }

        System.out.println(result);
    }

}
