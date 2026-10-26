import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> map1 = makeUp(str1);
        Map<String, Integer> map2 = makeUp(str2);
        
        Set<String> set = new HashSet<>(map1.keySet());
        set.addAll(map2.keySet());
        
        int sum = 0;
        int minus = 0;
        for(String s : set) {
            int num1 = map1.getOrDefault(s,0);
            int num2 = map2.getOrDefault(s,0);
            
            sum+= Math.max(num1,num2);
            minus += Math.min(num1,num2);
        }
        double result = 0;
    if (sum == 0) {
        return 65536; // 둘 다 공집합인 경우만 유사도 1
    }        else result = (double)minus/sum;
        return (int)(result*65536);
    }
    public static Map<String,Integer> makeUp(String str) {
        Map<String,Integer> map = new HashMap<>();
        str = str.toLowerCase();
        
        for(int i=0; i<str.length()-1; i++) {
            String s = str.substring(i,i+2);
            if(check(s.charAt(0)) && check(s.charAt(1))) {
                map.put(s, map.getOrDefault(s,0)+1);
            }
        }
        return map;
    }
    public static boolean check(char s) {
        return s>= 97 && s<=122;
    }
}