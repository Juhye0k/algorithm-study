import java.util.*;
class Node {
    int index;
    int value;
    public Node(int index, int value){
        this.index=index;
        this.value=value;
    }
}
class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Node> queue = new LinkedList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        for(int i=0; i<priorities.length; i++) {
            queue.add(new Node(i,priorities[i]));
            q.add(priorities[i]);
        }
        int count = 0;
        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            int num = q.peek();
            if(temp.value<num) {
                queue.add(temp);
            }
            else {
                q.poll();
                count++;
                if(location==temp.index) {
                    answer = count;
                    break;
                }
            }
        }
        return answer;
    }
}