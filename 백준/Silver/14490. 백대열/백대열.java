
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String str = br.readLine();

        String[] ar = str.split(":");

        int a= Integer.parseInt(ar[0]);
        int b= Integer.parseInt(ar[1]);



        int gcd = getGcd(a,b);
        System.out.println(a/gcd+":"+b/gcd);
    }
    public static int getGcd(int a, int b) {
        while(b!=0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}