import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        while(N>0){
            String str = String.valueOf(N);
            if(str.contains("1")&&str.length()!=1) {
                for(int i=0; i<str.length(); i++) {
                    if(str.charAt(i)=='1') {
                        count++;
                    }
                }
                str = str.replace("1","");
                if(str.isEmpty())
                    N=0;
                else
                    N = Integer.parseInt(str);
            }

            else {
                N--;
                count ++;
            }

        }
        System.out.println(count);
    }

}
