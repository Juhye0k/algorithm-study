
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 줄의 개수
        int N = Integer.parseInt(br.readLine());

        String s =  br.readLine();

        int d = 0,dk=0,dks=0, dksh =0;
        int result = 0 ;
        for(int i=0; i<N; i++) {
            char temp = s.charAt(i);
            if(temp=='D')
                d++;
            else if(temp=='K')
                dk+=d;
            else if(temp=='S')
                dks+=dk;
            else if(temp=='H')
                dksh+=dks;
        }
        System.out.println(dksh);
    }

}
