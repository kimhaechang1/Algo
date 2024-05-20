import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static HashMap<String, Integer> cache;

    static class Node{

        HashMap<Character, Node> map = new HashMap<>();
        boolean isEnd;

    }
    static class Trie{
        Node root;
        public Trie(){
            this.root = new Node();
        }

        public StringBuilder insert(String str){
            Node node = this.root;
            StringBuilder sb = new StringBuilder();
            boolean flag = true;
            for(int i= 0;i<str.length();i++){
                if(node.map.containsKey(str.charAt(i))){
                    if(flag){
                        sb.append(str.charAt(i));
                    }
                    node = node.map.get(str.charAt(i));
                }else{
                    if(flag){
                        sb.append(str.charAt(i));
                    }
                    flag = false;
                    node.map.put(str.charAt(i), new Node());
                    node = node.map.get(str.charAt(i));
                }
            }
            return sb;
        }

    }


    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        Trie trie = new Trie();
        cache = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        while(n-- > 0){
            String element= bf.readLine();
            if(cache.containsKey(element)){
                int count = cache.get(element);
                if(count + 1 >= 2){
                    cache.put(element, count+1);
                    sb.append(element).append(count+1).append("\n");
                }
            }else{
                cache.put(element, 1);
                StringBuilder result = trie.insert(element);
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
    }
}