import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int first = toMinutes(br.readLine());
        int second = toMinutes(br.readLine());
        int period1 = toMinutes(br.readLine());
        int period2 = toMinutes(br.readLine());


        List<String> list = new LinkedList<>();
        list.add("Saturday");
        list.add("Sunday");
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");

        long time1 = first;
        boolean found = false;

       for(int i=0; i<1000000; i++) {
           if(time1 >= second && (time1-second)%period2 ==0) {
               long dayIdx = (time1 / 1440) % 7;
               long hour = (time1 % 1440) / 60;
               long minute = time1 % 60;

               System.out.println(list.get((int)dayIdx));
               System.out.printf("%02d:%02d\n", hour, minute);
               found = true;
               break;
           }
           time1 += period1;
       }
        if(!found) {
            System.out.println("Never");
        }
    }

    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0])*60 + Integer.parseInt(parts[1]);
    }
}
