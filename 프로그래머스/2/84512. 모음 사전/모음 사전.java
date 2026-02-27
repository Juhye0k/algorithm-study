import java.util.*;
class Solution {
    static String[] list = {"A","E","I","O","U"};
    static int count = 0;
    static int answer;
    public int solution(String word) {
        answer = 0;
        String s="";
        dfs(s,0,word);
        return answer;

    }
    public static int dfs(String s,int depth, String word) {
        System.out.println(s+" "+word);
        if(s.equals(word)) {
        answer = count;
        return 0;
        }
        if(s.length()==5)
            return 0;
        
        
        for(int i=0; i<list.length; i++) {
            String temp=s;
            temp+=(list[i]);
            count++;
            dfs(temp,depth+1,word);
        }
        return -1;
    }
}