import java.io.*;
import java.util.*;
public class Main {

    static Stack<String> st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean isValid=true;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new Stack<>();
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                isValid=true;
                String s=str.substring(j,j+1);
                if(s.equals("(")){
                    st.push(s);
                }
                else if(s.equals(")"))
                {
                    if(st.isEmpty()){
                        isValid=false;
                        break;
                    }
                    else
                        st.pop();
                }
            }
            if(st.isEmpty()&&isValid)
            {
                bw.write("YES"+"\n");
            }
            else{
                bw.write("NO"+"\n");
            }
        }
            bw.flush();
            bw.close();
    }

}
