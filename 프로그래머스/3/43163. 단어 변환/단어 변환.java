import java.util.*;
class Node{
    String str;
    int count;
    public Node(String str, int count) {
        this.str = str;
        this.count = count;
    }
}
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = bfs(begin,target,words);
        return answer;
    }
    public static int bfs(String begin, String target, String[] words) {
        Set<String> set = new HashSet<>();
        set.add(begin);
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin,0));
        while(!q.isEmpty()) {
            Node temp = q.poll();
            String s = temp.str;
            if(s.equals(target)) return temp.count;
            for(int i=0; i<words.length; i++) {
                int count = 0;
                
                for(int j=0; j<begin.length(); j++) {
                    if(s.charAt(j)!=words[i].charAt(j)) 
                        count++;
                    if(count>=2) break;
                }
                
                if(count==1 && !set.contains(words[i])) {
                    q.add(new Node(words[i],temp.count+1));
                    set.add(words[i]);
                }
            }
        }
        return 0;
    }
}