import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;



class Node {
    int cal;
    int num;
    public Node(int cal, int num) {
        this.cal = cal;
        this.num = num;
    }
}
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 쿼리의 개수 Q
        int Q = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new  Stack<>();
        Stack<Node> temp = new Stack<>();
        for(int i=0; i<Q; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cal = Integer.parseInt(st.nextToken());
            // 1 i : 스택의 가장 위에 값 i를 넣기
            if(cal==1){
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
                temp.push(new Node(cal,num));
            }
            // 2 :  가장 위에 있는 값 제거
            else if (cal==2){
                int num = stack.pop();
                temp.push(new Node(cal,num));
            }
            // 3 : 최근 j개의 1번 또는 2번 쿼리 취소
            else if (cal==3){
                int num = Integer.parseInt(st.nextToken());
                for(int j=0; j<num; j++) {
                    Node node = temp.pop();
                    // 1이다 -> 스택에서 빼기
                    if(node.cal == 1){
                        stack.pop();
                    }
                    // 2이다 -> 스택에 다시 넣기
                    else {
                        stack.push(node.num);
                    }
                }
            }
            // 4 : 스택 크기 출력
            else if (cal==4){
                System.out.println(stack.size());
            }
            // 5 : 스택의 가장 위에 있는 값  출력, 비어있으면 -1 출력
            else {
                if(!stack.isEmpty())
                    System.out.println(stack.peek());
                else {
                    System.out.println(-1);
                }
            }
        }
    }
}