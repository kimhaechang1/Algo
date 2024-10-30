import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n, k;
    static int[] arr;

    public static void solve() {
       // 최대 k개의 집중국을 세울 수 있다.
        // 같은 지점에 여러개의 센서가 있을 수 있다.
        // 수신가능 영역이란 고속도로 상에서 연결된 구간으로 나타나게 된다. 그러한 구간 길이의 합의 최소
        // [1, 3, | 6, 7, 9] 바의 개수는 k-1개이고, 인접차가 가장 큰곳에 세우면 정답이 된다.
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =0;i<arr.length-1;i++) {
            if (arr[i+1] - arr[i] == 0) continue;
            pq.add(arr[i+1] - arr[i]);
        }
        int sum = 0;
        int cnt = pq.size() - (k - 1);
        while(cnt-- > 0) {
            sum += pq.poll();
        }
        System.out.print(sum);

    }
    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) arr[i] = Integer.parseInt(stk.nextToken());
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}