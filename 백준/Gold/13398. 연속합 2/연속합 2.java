import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int[] arr;

    public static void solve() {
        // 특정 하나의 원소를 지워야 하므로, 왼쪽에서 가장 최대의 합과 오른쪽에서의 가장 최대의 합 dp를 구하고
        // 원소별로 삭제하면서 최대일때를 찾는다.
        // 이 때 하나도 삭제하지 않는경우가 최대인 경우가 있으므로, 왼쪽에서부터 최대의 연속합을 구할때 ans를 갱신해야 한다.
        int[] ldp = new int[n];
        ldp[0] = arr[0];
        int[] rdp = new int[n];
        rdp[n - 1] = arr[n - 1];

        int ans = arr[0];
        // Integer.MIN_VALUE 로 할 경우 첫번째 원소가 가장 큰 경우가 고려되지 못한다.

        for(int i = 1;i<n;i++) {
            ldp[i] = Math.max(ldp[i - 1] + arr[i], arr[i]);
            // 제거를 안한 경우와의 비교도 필요하다.
            ans = Math.max(ldp[i], ans);
        }
        for(int i = n - 2;i>-1;i--) {
            rdp[i] = Math.max(rdp[i + 1] + arr[i], arr[i]);
        }
        for(int i = 1;i<n - 1;i++) {
            ans = Math.max(ans, ldp[i - 1] + rdp[i + 1]);
        }
        System.out.print(ans);

    }
    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) arr[i] = Integer.parseInt(stk.nextToken());
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}