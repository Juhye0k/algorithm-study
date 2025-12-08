
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {

    Map<String, Node> children = new TreeMap<>();
}


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 경로의 개수
        int N = Integer.parseInt(br.readLine());

        Node root = new Node();

        for(int i=0; i<N; i++){
            String str =  br.readLine();
            String[] strs = str.split("\\\\");
            // 만약에 루트에 없다 -> 노드 생성
            // 루트에 있다 -> 넣기?
            insert(root, strs);
        }
        // 문자열 제시할 때 가장 앞에께 루트. 그다음 서브 디렉토리
        print(root, 0);
    }
    static void insert(Node root, String[] parts) {
        Node current = root;
        for(String s: parts) {
            current.children.putIfAbsent(s, new Node());
            current = current.children.get(s);
        }
    }
    static void print(Node node, int depth) {
        for(String key: node.children.keySet()) {
            for(int i=0; i<depth; i++) {
                System.out.print(" ");
            }
            System.out.println(key);
            print(node.children.get(key), depth+1);
        }
    }
}
