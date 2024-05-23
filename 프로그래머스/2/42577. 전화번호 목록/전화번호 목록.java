import java.util.*;

class Solution {
    static class Node{
        HashMap<Character, Node> map = new HashMap<>();
        boolean isEnd;
    }
    static class Trie{
        Node root;
        public Trie(){
            this.root = new Node();
        }
        public void insert(String str){
            char [] frags = str.toCharArray();
            Node now = this.root;
            for(int i = 0;i<frags.length;i++){
                if(now.map.containsKey(frags[i])){
                    now = now.map.get(frags[i]);
                }else{
                    now.map.put(frags[i], new Node());
                    now = now.map.get(frags[i]);
                }
            }
            now.isEnd = true;
        }
        public boolean search(String str){
            char [] frags = str.toCharArray();
            Node now = this.root;
            for(int i = 0;i<frags.length;i++){
                Node element = now.map.get(frags[i]);
                if(i != frags.length-1 && element.isEnd){
                    return false;
                }
                now = element;
            }
            return true;
        }
    }
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Trie trie = new Trie();
        for(int i = 0;i<phone_book.length;i++){
            trie.insert(phone_book[i]);
        }
        for(String item: phone_book){
            if(!trie.search(item)){
                return false;
            }
        }
        return true;
    }
}