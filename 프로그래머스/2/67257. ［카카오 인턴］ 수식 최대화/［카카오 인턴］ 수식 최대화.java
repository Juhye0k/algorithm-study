import java.util.*;

class Solution {
    static List<Long> numbers;
    static List<String> operators;
    
    static String[] kinds = {"+", "-", "*"};
    
    static boolean[] visited;
    static long answer;
    
    public long solution(String expression) {
        answer = 0;
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        
        StringBuilder number = new StringBuilder();
        
        for(int i=0; i<expression.length(); i++) {
            char ch = expression.charAt(i);
            if(ch>='0' && ch<='9') number.append(ch);
            
            else {
                numbers.add(Long.parseLong(number.toString()));
                number.setLength(0);
                
                operators.add(String.valueOf(ch));
            }
        }
        numbers.add(Long.parseLong(number.toString()));
        visited = new boolean[3];
        
        dfs(new ArrayList<>());
        return answer;
    }
    public static void dfs(List<String> list) {
        if(list.size()==3) {
            long result = Math.abs(bfs(list));
            answer = Math.max(answer,result);
            return;
        }
        for(int i=0; i<3; i++) {
            if(!visited[i]) {
                list.add(kinds[i]);
                visited[i] = true;
                dfs(list);
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
    public static long bfs(List<String> list) {
        
        List<Long> nums = new ArrayList<>(numbers);
        List<String> ops = new ArrayList<>(operators);
        
        for(String s : list) {
            int i=0;
            
            while(i<ops.size()) {
                if(ops.get(i).equals(s)) {
                    long result = calculate(s,nums.get(i),nums.get(i+1));
                       nums.set(i,result);
                nums.remove(i+1);
                ops.remove(i);
                }
             
            
            else{
                i++;
            }
            }
        }
        return nums.get(0);
    }
    public static long calculate(String str, long num1, long num2) {
        if(str.equals("*")){
            return num1 * num2;
        }
        else if(str.equals("-"))
            return num1-num2;
        else
            return num1+num2;
    }
}