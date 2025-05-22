import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int t;
    static int n;
    static String s;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
            m.input();
            sb.append(m.solve()).append("\n");
        }
        System.out.print(sb);
    }

    void input() throws Exception {

        n = Integer.parseInt(bf.readLine());
        s = bf.readLine();
    }

    String solve() {

        // 'A', 'B' 로 만 이루어진 S
        // 'ABB' 가 등장한 위치를 i
        Stack<String> stack = new Stack<>();

        Deque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            deque.add(s.charAt(i));
        }
        while(!deque.isEmpty()) {
            char val = deque.pollFirst();
            if (stack.isEmpty() || val == 'A') {
                stack.push(String.valueOf(val));
            } else if (!stack.isEmpty() && val == 'B') {
                if (stack.peek().equals("A")) {
                    stack.pop();
                    stack.push("AB");
                } else if (stack.peek().equals("AB")) {
                    stack.pop();
                    deque.addFirst('A');
                    deque.addFirst('B');
                } else {
                    stack.push("B");
                }
            }
        }
        String result = "";
        while(!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }

}