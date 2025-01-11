import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set=new HashSet<>();
        String s=br.readLine();
        for(int i=0;i<s.length();i++){
            for(int j=0;j+i<s.length();j++){
                String str=s.substring(j,j+i+1);
                set.add(str);
            }
        }
        System.out.println(set.size());
    }


}
