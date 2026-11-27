import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

class Person {
    String name;
    String day;
    String month;
    String year;
    public Person(String name, String day, String month, String year) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.name = name;
    }
}



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

       int n = Integer.parseInt(br.readLine());

       List<Person> list = new ArrayList<>();
       for(int i=0;i<n;i++){
           StringTokenizer st = new StringTokenizer(br.readLine());

           list.add(new Person(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
       }
       Collections.sort(list, (o1, o2)-> {
           if (o1.year.equals(o2.year)) {
               if (o1.month.equals(o2.month)) {
                   return Integer.compare(Integer.parseInt(o1.day), Integer.parseInt(o2.day));
               }
               return Integer.compare(Integer.parseInt(o1.month), Integer.parseInt(o2.month));
           }
           return o1.year.compareTo(o2.year);
       });
        System.out.println(list.get(n-1).name);
        System.out.println(list.get(0).name);
    }
}