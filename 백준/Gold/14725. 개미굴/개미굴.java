import java.util.*;
import java.io.*;

public class Main{

    static class Node{
        TreeMap<String, Node> map = new TreeMap<>();
        boolean isEnd;
    }
    static class Trie{
        Node root;
        public Trie(){
            this.root = new Node();
        }

        public void insert(int k, StringTokenizer stk){
            Node node = this.root;
            while(stk.hasMoreTokens()){
                String element = stk.nextToken();
                if(node.map.containsKey(element)){
                    node = node.map.get(element);
                }else{
                    node.map.put(element, new Node());
                    node = node.map.get(element);
                }
            }
        }
        public void search(Node node, int depth, StringBuilder sb){
            if(node.map.size() == 0){
                return;
            }
            for(Map.Entry<String, Node> entry: node.map.entrySet()){
                String element = entry.getKey();
                for(int i= 0;i<depth;i++){
                    sb.append("--");
                }
                sb.append(element).append("\n");
                search(node.map.get(element), depth+1, sb);
            }
        }
    }

    static int n;
    static StringTokenizer stk;


    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        Trie trie = new Trie();
        for(int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int k = Integer.parseInt(stk.nextToken());
            trie.insert(k, stk);
        }
        Node root = trie.root;
        StringBuilder sb= new StringBuilder();
        trie.search(root, 0, sb);
        System.out.print(sb);
    }
}