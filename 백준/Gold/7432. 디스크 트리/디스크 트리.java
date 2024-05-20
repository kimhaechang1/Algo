import java.util.*;
import java.io.*;

public class Main{

    static int n;
    static StringTokenizer stk;

    static class Node{
        TreeMap<String, Node> map = new TreeMap<>();
    }
    static class Trie{
        Node root;
        public Trie(){
            this.root = new Node();
        }

        public void insert(StringTokenizer stk){
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

        public void search(int depth, Node node, StringBuilder sb){
            if(node.map.size() == 0){
                return;
            }
            for(Map.Entry<String, Node> entry: node.map.entrySet()){
                String element = entry.getKey();
                for(int i = 0;i<depth;i++){
                    sb.append(" ");
                }
                sb.append(element).append("\n");
                search(depth+1, node.map.get(element), sb);
            }
        }
    }

    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        Trie trie = new Trie();
        while(n-- > 0){
            stk = new StringTokenizer(bf.readLine(),"\\");
            trie.insert(stk);
        }
        StringBuilder sb= new StringBuilder();
        trie.search(0, trie.root, sb);
        System.out.print(sb);
    }
}