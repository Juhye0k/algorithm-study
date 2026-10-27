import java.util.*;

class Solution {
    public int[] solution(String s) {
        
        String str = s.substring(2,s.length()-2);
        
        String[] arr = str.split("\\},\\{");
        Arrays.sort(arr, (a,b)->a.length()-b.length());
        int index = 0;
        int[] answer = new int[arr.length];
        Set<String> set = new HashSet<>();
        for(int i=0; i<arr.length; i++) {
            String[] a = arr[i].split(",");
            for(int j=0; j<a.length; j++) {
                if(!set.contains(a[j])) {
                    set.add(a[j]);
                    answer[index++] = Integer.parseInt(a[j]);
                }
            }
        }
        
        return answer;
    }
}