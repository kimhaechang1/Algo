import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n, k;
    static int[] arr;

    public static void solve() {
        // 각 조에는 적어도 한명의 원생이 있어야함, 서로 인접한 원생들끼리 같은 조여야함
        // 조별로 인원이 같을필요는 없음, 가장 큰 원생과 가장 작은 원생의 키 차이만큼 비용이 발생함
        // 합을 최소로 하고싶음
        // n = 5, k = 3 이면 5명을 3등분해야함 그럴려면 2개의 구분자가 필요함
        // 1 3 5 6 10 에서 모든 인접차를 구하면
        //  2 2 1 4     가 나오고 사용하지 않을 인접차를 없애게 되면 최소가 됨
        // 1 3 | 5 6 | 10
        // 아 구분자의 개수인 K - 1이 곧 배제할 인접차의 개수구나
        int[] chas = new int[n-1];
        for(int i = 0;i<n-1;i++) {
            chas[i] = arr[i+1] - arr[i];
        }
        // [1 2 2 4]
        Arrays.sort(chas);
        long ans = 0;
        for(int i = chas.length - 1 - (k - 1); i > -1 ; i --) ans += chas[i];
        System.out.print(ans);

    }
    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) arr[i] = Integer.parseInt(stk.nextToken());
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}