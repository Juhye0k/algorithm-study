import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (isGroupWord(word)) {
                count++;
            }
        }
        
        System.out.println(count);
    }
    
    static boolean isGroupWord(String word) {
        Set<Character> seen = new HashSet<>();
        char prev = '\0';
        
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            
            if (current != prev) {
                if (seen.contains(current)) {
                    return false;
                }
                seen.add(current);
                prev = current;
            }
        }
        
        return true;
    }
}