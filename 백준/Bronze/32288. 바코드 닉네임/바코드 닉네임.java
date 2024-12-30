

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        String str=br.readLine();
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='I')
                System.out.print("i");
            else if(str.charAt(i)=='l')
                System.out.print('L');
        }

    }

}
