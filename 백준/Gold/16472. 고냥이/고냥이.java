import java.io.*;
import java.sql.SQLOutput;
import java.util.*;





public class Main {

    static int[][] ar;

    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        Map<Character, Integer> map = new HashMap<>();

        int left = 0;

        int answer = 0;
        int result = 0;


        for (int right = 0; right<str.length(); right++) {
            char added = str.charAt(right);
            map.put(added, map.getOrDefault(added,0)+1);

            while(map.size()>N) {
                char removed = str.charAt(left);
                int count = map.get(removed);
                if(count == 1) map.remove(removed);
                else map.put(removed, count-1);
                left++;
            }
            answer = Math.max(right-left+1, answer);
        }


        System.out.println(answer);
    }
}