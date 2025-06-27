import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[] arr;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 어떤 입력값 배열에 대해서 가리키는 인덱스가 가리키는 값이 0이라면 오른쪽으로 하나씩 인덱스를 더하고
        // 인덱스에 해당하는 값이 0이 아니라면, 그 값을 출력하고 그 값에 0을 매긴 뒤, (값 + 인덱스) % N 을 한 만큼 인덱스를 옮긴다.

        // 5
        // 1 2 4 3 5 의 예시로 생각해보자.
        // 항상 첫 인덱스에 해당하는 값은 항상 양의 정수이기에 출력된다.
        // 그 값에 결과에 따라 0번 인덱스에는 반드시 1이 있어야 한다.
        // 그리고 반드시 다음 from 은 (value + from) % n 이기에 1이 된다.
        // 그렇다는것은 현재 입력의 1번째 인덱스 원소인 2가 복원된 장소에서의 (value + from)% N 의 위치에 있어야한단 것이다.
        // 여기까지 우리는 1 -> 2 를 유추해볼 수 있다.
        // 그 다음으로는 from은 자동적으로 (2 + 1) % N 에 의거하여 3번째 인덱스에 위치해야하며, 해당 원소는 4가 된다.
        // 그렇다면 복원배열은 [1, 2, X, 4, X] 가 된다.
        // 다음 인덱스는 (4 + 3) % N 이기에 2이고, 2번째 인덱스가 3이어야 한단 얘기다.
        // [1, 2, 3, 4, X] 가 된다.
        // 다음 인덱스는 (2 + 2) % N 이기에 4이고, 4번째 인덱스의 원소가 곧 5여야 한단 이야기다.
        // 총 복원된 배열은 다음과같이 [1, 2, 3, 4, 5] 가 된다.

        int[] rec = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        int from = 0;
        rec[from] = arr[0];
        int value = arr[0];
        from = (value + from) % n;
        for(int i= 1; i <n; i++) queue.add(arr[i]);
        while(!queue.isEmpty()) {
            while(from < n && rec[from] != 0) {
                from = (from + 1) % n;
            }
            int now = queue.poll();
            rec[from] = now;
            value = now;
            from = (value + from) % n;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for(int r: rec) sb.append(r).append(" ");
        System.out.print(sb);
    }

    void testCase() throws Exception {

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }
}