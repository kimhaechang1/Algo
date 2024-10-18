import java.util.*;

class Solution {
    // 접두사 trie
    // 접미사 trie
    class Trie {
        
        Node head;
        
        public Trie() {
            head = new Node();
        }
        
        public void insert(String word) {
            char[] alphas = word.toCharArray();
            Node pres = head;
            pres.lengthCnt.put(word.length(), pres.lengthCnt.getOrDefault(word.length(), 0) + 1);
            for(int i = 0;i< alphas.length;i++) {
                char alpha = alphas[i];
                Node nxt = pres.map.get(alpha);
                if (nxt == null) {
                    Node newNode = new Node(word.length());
                    pres.map.put(alpha, newNode);
                    pres = pres.map.get(alpha);
                } else {
                    nxt.lengthCnt.put(word.length(), nxt.lengthCnt.getOrDefault(word.length(), 0) + 1);
                    pres = nxt;
                }
            }
        }
        public int search(String find, int depth, int total) {
            Node pres = head;
            int idx = 0;
            boolean isMatched = true;
            while(idx < depth && isMatched) {
                pres = pres.map.get(find.charAt(idx));
                if (pres == null) isMatched = false;
                idx++;
            }
            if (!isMatched) return 0;
            return pres.lengthCnt.getOrDefault(total, 0);
        }
        
    }
    class Node {
        HashMap<Character, Node> map = new HashMap<>();
        boolean isEnd;
        HashMap<Integer, Integer> lengthCnt = new HashMap<>();
        public Node() {
        }
        public Node(int len) {
            isEnd = false;
            lengthCnt.put(len, lengthCnt.getOrDefault(len, 0) + 1);
        }
    }
    public int[] solution(String[] words, String[] queries) {
        ArrayList<Integer> ansList = new ArrayList<>();
        Trie head = new Trie();
        Trie end = new Trie();
        
        for(String word: words) {
            head.insert(word);
            end.insert(reverse(word));
        }
        
        for(String q: queries) {
            if (q.startsWith("?")) {
                String rev = reverse(q);
                int first = rev.indexOf("?");
                String find = rev.substring(0, first);
                ansList.add(end.search(find, find.length(), q.length()));
            } else { 
                int first = q.indexOf("?");
                String find = q.substring(0, first);
                ansList.add(head.search(find, find.length(), q.length()));
            }
            
        }
        int[] ans =new int[ansList.size()];
        for(int i = 0;i<ans.length;i++){
            ans[i] = ansList.get(i);
        }
        return ans;
    }
    public String reverse(String word) {
        StringBuilder sb = new StringBuilder();
        for(int i = word.length() - 1; i> -1; i--) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}