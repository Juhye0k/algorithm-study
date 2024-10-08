import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st=new Stack<>();
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++)
        {
            String a[]=br.readLine().split(" ");
            String com=a[0];
            switch(com) {
                case "push":
                    int n=Integer.parseInt(a[1]);
                    st.push(n);
                    break;
                case "top":
                    if(st.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(st.peek());
                    break;
                case "size":
                    System.out.println(st.size());
                    break;
                case "pop":
                    if(st.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(st.pop());
                    break;
                case "empty":
                    if(st.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;

            }
        }



    }
}