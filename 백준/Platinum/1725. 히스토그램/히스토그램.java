import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static StringTokenizer stk;
    static BufferedReader bf;


    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void testCase() throws Exception {
        StringBuilder sb = new StringBuilder();
        while(true) {
            input();
            if (n == 0) break;
            sb.append(TC()).append("\n");
        }
        System.out.print(sb);
    }

    void input() throws Exception {
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        for(int i = 0;i < n ;i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
    }

    int TC() {
       return 0;
    }

    void solve() {
        long answer = 0;
        // 문제를 관찰해보면
        // 특정 히스토그램을 기준으로 양옆으로 펼쳐나갔을 때의 최댓값을 구해야 한다.
        // 그리고 특정 히스토그램을 기준으로 왼쪽의 값이 오름차순이라면, 꺼낼 때마다 해당 인덱스의 높이가 항상 최소가 된다.
        // 또한 해당 인덱스까지의 너비가 항상 넓이가 된다.

        // 즉, 오름차순을 유지할 수 있는 순간이 깨질 때마다 각 히스토그램별 넓이를 구할 수 있다.
        // 왜냐하면 그때가 스택내부의 오름차순 유지되는 히스토그램의 값이 최소임이 보장되니까

        long[] leftMaxArea = new long[n];
        long[] rightMaxArea = new long[n];
        for(int i = 0;i < n; i++) {
            leftMaxArea[i] = arr[i];
            rightMaxArea[i] = arr[i];
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < n; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int idx = stack.pop();
                int minHeight = arr[idx];
                int width = i - idx;
                leftMaxArea[idx] = Math.max(leftMaxArea[idx], (long)minHeight * width);
            }
            stack.push(i);
        }
        // 끝까지 넣었는데도 불구하고 오름차순이 항상 유지된 경우
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            int minHeight = arr[idx];
            int width = n - idx;
            leftMaxArea[idx] = Math.max(leftMaxArea[idx], (long)minHeight * width);
        }
        stack = new Stack<>();
        for(int i = n - 1;i > -1; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int idx = stack.pop();
                int minHeight = arr[idx];
                int width = Math.abs(i - idx);
                rightMaxArea[idx] = Math.max(rightMaxArea[idx], (long)minHeight * width);
            }
            stack.push(i);
        }
        // 끝까지 넣었는데도 불구하고 오름차순이 항상 유지된 경우
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            int minHeight = arr[idx];
            int width = idx + 1;
            rightMaxArea[idx] = Math.max(rightMaxArea[idx], (long)minHeight * width);
        }


        for(int i = 0;i < n; i++) {
            answer = Math.max(answer, leftMaxArea[i] + rightMaxArea[i] - arr[i]);
        }

        System.out.print(answer);
    }

}