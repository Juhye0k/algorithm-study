

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Long n=sc.nextLong();
        if(n<=32767 && n>=-32768){
            System.out.println("short");
        }
        else if(n>=-2147483648 && n<=2147483647){
            System.out.println("int");
        }
        else
            System.out.println("long long");

    }

}
