import java.util.*;
import java.io.*;
class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(int[] numbers, int target) {
        answer = 0;
        visited = new boolean[numbers.length+1];
        dfs(0,numbers, 0,numbers.length, target);
        return answer;
    }
    public static void dfs(int result, int[] numbers, int depth, int size, int target) {
       if(depth == size) {
           if(result == target)
              answer+=1;
            return;
       }
        dfs(result-numbers[depth],numbers, depth+1,size,target);
        dfs(result+numbers[depth], numbers, depth+1, size, target);
    }
}