import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int k;
    static StringTokenizer stk;
    static BufferedReader bf;
    static String number;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void input() throws Exception{
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        number = bf.readLine();

    }

    void solve() {
        int need = n - k;
        // 그냥 애초에 생략이고 뭐고 생각할 필요 없이, 큰 수를 만들려면
        // 자리에 있는 수가 모두 9이거나 최소한 젤 앞의 수로 갈수록 커야 한다.
        // 즉 내림차순을 유지할 수 있어야함
        int[] arr = new int[n];
        for(int i = 0;i<n;i++) arr[i] = number.charAt(i) - '0';
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<n;i++) {
            while(!stack.isEmpty() && k > 0 && stack.peek() < arr[i]) {
                k--;
                stack.pop();
            }
            if (stack.size() >= need) continue;
            stack.push(arr[i]);
        }
        Stack<Integer> stack2 = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) stack2.push(stack.pop());
        while(!stack2.isEmpty()) sb.append(stack2.pop());
        System.out.print(sb);

    }

}