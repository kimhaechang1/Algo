import java.util.*;
// start 19:26
// end
class Solution {
    class Node{
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
        int count; // 예상 검색어 개수
    }
    
    class Trie{
        Node root;
        public Trie(){
            root = new Node();
        }
        public void insert(String str){
            Node next = this.root;
            for(int i= 0;i<str.length();i++){
                if(next.child.containsKey(str.charAt(i))){
                    next = next.child.get(str.charAt(i));
                    next.count = next.count + 1;
                }else{
                    next.child.put(str.charAt(i), new Node());
                    next = next.child.get(str.charAt(i));
                    next.count += 1;
                }
            }
            next.isEnd = true;
        }
        
        public int search(String str){
            Node next = this.root;
            int cnt = 0;
            boolean isBreak = false;
            for(int i= 0;i<str.length();i++){
                Node element = next.child.get(str.charAt(i));
                if(element.count == 1){
                    cnt = i+1;
                    isBreak = true;
                    break;
                }
                next = element;
            }
            if(!isBreak){
                cnt = str.length();
            }
            return cnt;
        }
    }
    
    public int solution(String[] words) {
        Trie trie = new Trie();
        // 트라이가 어쨋든 문자열 검사니까
        // 대응되는 문자열의 개수가 1개가 되는순간 해당 문자로 검색이 가능하다.
        for(String word: words){
            trie.insert(word);
        }
        int sum = 0;
        for(String word: words){
            sum+= trie.search(word);
        }
        return sum;
    }
}