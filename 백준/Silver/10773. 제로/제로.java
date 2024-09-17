
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st=new Stack<>();
        int result=0;
        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            int n=Integer.parseInt(br.readLine());
            if((st.isEmpty()==false) && n==0)
            {
                st.pop();
            }
            else
            {
                st.push(n);
            }


        }
        while(st.isEmpty()==false)
        {
            if(st.isEmpty())
                break;
            int a=st.pop();
            result=result+a;
        }
        System.out.println(result);






    }
}