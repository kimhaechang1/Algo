import java.util.*;
import java.io.*;


public class Main{
    static String [] strings;

    static class Node{
        HashMap<Character, Node> child = new HashMap<>();
        boolean isEnd;
        int count;
    }
    static class Trie{

        Node root;
        public Trie(){
            this.root = new Node();
        }

        public void insert(String str){
            Node p = this.root;
            for(int i = 0;i<str.length();i++){
                if(p.child.containsKey(str.charAt(i))){
                    p = p.child.get(str.charAt(i));
                    p.count++;
                }else{
                    p.child.put(str.charAt(i), new Node());
                    p = p.child.get(str.charAt(i));
                    p.count++;
                }
            }
            p.isEnd = true;
        }

        public boolean search(String str){
            Node p = this.root;
            for(int i = 0;i<str.length();i++){
                Node element = p.child.get(str.charAt(i));
                if(str.length() != i+1 && element.isEnd){
                    return false;
                }
                p = element;
            }
            return true;
        }
    }

    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb= new StringBuilder();
        while(T-- > 0){
            int n = Integer.parseInt(bf.readLine());
            strings = new String[n];
            Trie trie = new Trie();
            for(int i = 0;i<n;i++){
                strings[i] = bf.readLine();
                trie.insert(strings[i]);
            }
            String ans = "YES";
            for(String str: strings){
                if(!trie.search(str)){
                    ans = "NO";
                    break;
                }
            }

            sb.append(ans).append("\n");

        }
        System.out.print(sb);

    }
}