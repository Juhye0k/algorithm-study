import java.io.*;
import java.util.*;


public class Main {
    static char tree[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        tree=new char[26][2];
        int N=Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            char parent=st.nextToken().charAt(0);
            char leftChild=st.nextToken().charAt(0);
            char rightChild=st.nextToken().charAt(0);
            tree[parent-'A'][0]=leftChild;
            tree[parent-'A'][1]=rightChild;
        }
        preorder('A',sb);
        sb.append("\n");
        inorder('A',sb);
        sb.append("\n");
        postorder('A',sb);
        sb.append("\n");
        System.out.println(sb);

    }
    public static void preorder(char node,StringBuilder sb){
        if(node=='.')
            return;
        sb.append(node);
        preorder(tree[node-'A'][0],sb);
        preorder(tree[node-'A'][1],sb);
    }
    public static void inorder(char node,StringBuilder sb){
        if(node=='.')
            return;
        inorder(tree[node-'A'][0],sb);
        sb.append(node);
        inorder(tree[node-'A'][1],sb);
    }
    public static void postorder(char node,StringBuilder sb){
        if(node=='.')
            return;
        postorder(tree[node-'A'][0],sb);
        postorder(tree[node-'A'][1],sb);
        sb.append(node);
    }

}

