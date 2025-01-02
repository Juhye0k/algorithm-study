import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args)  throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        List<Integer> ar=new ArrayList<>();

        st=new StringTokenizer(br.readLine());

        //함수 결과
        for(int i=0;i<10;i++)
            ar.add(Integer.parseInt(st.nextToken()));

        st=new StringTokenizer(br.readLine());
        String fa=st.nextToken();
        String fb=st.nextToken();
        String a="";
        String b="";
        String result = "";
        for(int i=0;i<fa.length();i++)
        {
            int x=Integer.parseInt(fa.substring(i,i+1));
            for(int j=0;j<10;j++)
            {
                if(ar.get(j)==x)
                {
                    a=a+j;
                    break;
                }

            }
        }
        for(int i=0;i<fb.length();i++)
        {
            int x=Integer.parseInt(fb.substring(i,i+1));
            for(int j=0;j<10;j++)
            {
                if(ar.get(j)==x)
                {
                    b=b+j;
                    break;
                }
            }
        }
        String ab= String.valueOf(Integer.parseInt(a)+Integer.parseInt(b));
        for(int i=0;i<ab.length();i++)
        {
            int x=Integer.parseInt(ab.substring(i,i+1));
            result=result+ar.get(x);
        }
        System.out.println(result);
    }
}
