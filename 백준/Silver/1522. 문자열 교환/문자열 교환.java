import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s=br.readLine();
        String str[]=new String[s.length()];
        int size=0;
        for(int i=0;i<s.length();i++){
            str[i]= String.valueOf(s.charAt(i));
        }
        for(int i=0;i<str.length;i++){
            if(str[i].equals("a"))
                size++;
        }
        int result=0;
        for (int i = 0; i < str.length; i++) {
            int a = 0;
            for (int k = 0; k < size; k++) {
                int j = (i + k) % str.length;
                if (str[j].equals("a"))
                    a++;
            }
            result = Math.max(result, a);
        }
        System.out.println(size-result);
    }
}
