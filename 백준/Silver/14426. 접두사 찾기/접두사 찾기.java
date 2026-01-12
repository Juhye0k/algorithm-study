import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}

class Trie {
    TrieNode root = new TrieNode();

    // 문자열을 삽입하여 집합 S 만들기
    public void insert(String str) {
        TrieNode node = root;
        for(char c : str.toCharArray()) {
            // 문자를 0~25 인덱스로 변환
            int index = c-'a';
            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // 접두사 검사 (입력받은 str이 트라이에 있는 단어의 접두사인지)
    public boolean isPrefix(String str) {
        TrieNode node = root;
        for(char c : str.toCharArray()) {
            int index = c-'a';
            // 해당 길이 없다 -> 문자열의 접두사가 아님
            if(node.children[index] == null) return false;
            node = node.children[index];
        }
        // 끝까지 갔다면 어떤 경로의 일부이다
        return true;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        // 문자열의 개수 N
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        // 집합 S의 문자열을 트라이에 삽입
        for(int i=0; i<N; i++) {
            trie.insert(br.readLine());
        }
        int count = 0;
        for(int i=0; i<M; i++) {
            String checkStr = br.readLine();
            if(trie.isPrefix(checkStr)) count++;
        }
        System.out.println(count);


    }
}
