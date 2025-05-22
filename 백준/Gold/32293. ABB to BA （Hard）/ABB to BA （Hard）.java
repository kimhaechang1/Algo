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
        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            deque.addLast(s.charAt(i));
        }

        while(!deque.isEmpty()) {
            char val = deque.pollFirst();
            if (stack.isEmpty() || val == 'A') {
                stack.push(val);
            } else if (stack.size() >= 2 && val == 'B') {

                char i1 = stack.pop();
                char i0 = stack.pop();
                if (i0 == 'A' && i1 == 'B') {
                    deque.addFirst(i0);
                    deque.addFirst(i1);
                } else {
                    stack.push(i0);
                    stack.push(i1);
                    stack.push(val);
                }
            } else {
                stack.push(val);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

}