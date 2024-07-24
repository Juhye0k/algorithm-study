import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        String ar[]=new String[N];
        for(int i=0;i<N;i++)
        {
            ar[i]=br.readLine();

        }

        Arrays.sort(ar,(a1,a2)->{
            if(a1.length()==a2.length())
            {
               return a1.compareTo(a2);
            }
            else
                return a1.length()-a2.length();
        });
        String[] resultArr=Arrays.stream(ar).distinct().toArray(String[]::new);

        for (String s : resultArr) {
            System.out.println(s);
        }



    }
}
