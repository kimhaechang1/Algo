import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static String[] s;
    static int q;
    static String[] qString;
    static BufferedReader bf;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();

    }

    void input() throws Exception {

        n = Integer.parseInt(bf.readLine());
        s = new String[n];
        for(int i = 0; i < n; i++) {
            s[i] = bf.readLine();
        }
        q = Integer.parseInt(bf.readLine());
        qString = new String[q];
        for(int i = 0; i < q; i++) {
            qString[i] = bf.readLine();
        }
    }
    static class Node {

        HashMap<Character, Node> node;
        boolean output;
        Node fail;

        public Node() {
            node = new HashMap<>();
        }
    }

    static class Trie {

        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {

            char[] chars = word.toCharArray();
            Node current = root;
            for(char c: chars) {
                if (current.node.containsKey(c)) {
                    current = current.node.get(c);
                } else {
                    current.node.put(c, new Node());
                    current = current.node.get(c);
                }
            }
            current.output = true;
        }

        public void linkFail() {
            Queue<Node> queue = new ArrayDeque<>();
            this.root.fail = this.root;
            queue.add(this.root);
            
            while(!queue.isEmpty()) {
                Node now = queue.poll();
                
                for(int i = 0; i < 26; i++) {
                    char target = (char) ('a' + i);
                    if (!now.node.containsKey(target)) continue;
                    
                    Node next = now.node.get(target);
                    if (now == this.root) {
                        next.fail = this.root;
                    } else {
                        Node dest = now.fail;
                        while(dest != this.root && !dest.node.containsKey(target)) {
                            dest = dest.fail;
                        }
                        if (dest.node.containsKey(target)) {
                            next.fail = dest.node.get(target);
                        } else {
                            // 실패함수를 결국 root 를 제외하고 못찾았으면 root로 연결해야함
                            next.fail = this.root;
                        }
                    }
                    
                    if (next.fail.output) next.output = true;
                    queue.add(next);
                }
            }
        }

        public boolean find(String word) {
            Node current = root;
            char[] chars = word.toCharArray();
            for(char c: chars) {
                while(current != this.root && !current.node.containsKey(c)) {
                    current = current.fail;
                }
                if (current.node.containsKey(c)) {
                    current = current.node.get(c);
                }
                if (current.output) {
                    return true;
                }
            }
            return false;
        }
    }

    void solve() {

        StringBuilder sb = new StringBuilder();
        Trie trie = new Trie();
        for(int i = 0; i < n; i++) {
            trie.insert(s[i]);
        }
        trie.linkFail();

        for(int i = 0; i < q; i++) {
            sb.append(trie.find(qString[i]) ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }
}