


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        int count = 0;
        for(int i = 0; i < n; i++) {
            words[i] = normalize(br.readLine());
        }

        for(int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(words[i].equals(words[j])) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    private static String normalize(String word) {
        Map<Character, Character> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char nextPatternChar = 'a';

        for(char c : word.toCharArray()) {
            if(!map.containsKey(c)) {
                map.put(c, nextPatternChar++);
            }
            sb.append(map.get(c));
        }
        return sb.toString();
    }
}