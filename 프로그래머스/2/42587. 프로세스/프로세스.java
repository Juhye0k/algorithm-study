import java.util.*;
class Node {
    int value;
    int location;
    public Node (int value, int location) {
        this.value= value;
        this.location = location;
    }
}
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        /*
        A B C D 
        B C D A
        C D A B
        D A B
        A B
        B
        C -> D -> A -> B
        */
        Queue<Node> q = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            q.add(new Node(priorities[i],i));
        }
        int max = Integer.MIN_VALUE;
        int count = 0;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            boolean hasHigher = false;
            for(Node process : q) {
                if(process.value>temp.value) {
                    hasHigher = true;
                    break;
                }
            }
            if(hasHigher) {
                q.offer(temp);
            } else {
                count ++;
                if(temp.location == location) 
                    return count;
            }
        }
        
        return count;
    }
}