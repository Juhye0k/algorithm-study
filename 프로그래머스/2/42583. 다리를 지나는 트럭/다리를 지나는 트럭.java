import java.util.*;

class Node{
    int time;
    int weight;
    public Node(int time, int weight) {
        this.time=time;
        this.weight=weight;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Node> q = new LinkedList<>();
        int nowWeight = 0;
        int index=0;
        while(index<truck_weights.length) {
             answer++;
                if(!q.isEmpty() && answer-q.peek().time==bridge_length) {
                    Node node = q.poll();
                    nowWeight-=node.weight;
                }
                

            // 만약 지금 넣고자 하는 트럭의 무게가 다리 무게 감당 가능
            int temp = truck_weights[index];

            if(nowWeight+temp<=weight) {
                q.add(new Node(answer,truck_weights[index]));
                nowWeight+=truck_weights[index];
                index++;
            }
        }
        return answer+bridge_length;
    }
}