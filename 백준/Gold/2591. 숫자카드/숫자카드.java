import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;

    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String numStr = bf.readLine();
        n = numStr.length();
        arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = numStr.charAt(i) - '0';
        }

        // 결국 어떤 수에 대해서 단독으로 쓰는 경우, 해당 번째수까지를 34이하의 두자리 수로 묶는 경우의수로 나뉜다.
        // edge: 그런데 현재수가 0이면, 무조건 앞의 수와 합쳐서 두자리 수로 묶여야 한다.
        // 그게아닐경우에는 이전 수를 10의 자리수로 생각하고 합쳤을 때 34 이하라면,
        // 현재자리수 까지를 두자리수로 묶는 경우 dp[i][1] 와, 현재 자리수를 단독으로 생각하는 경우 dp[i][0] 을 둘다 구해야한다.
        // 만약 34를 초과한다면 현재수를 무조건 단독으로 써야 한다.
        // 현재수를 단독으로 사용한다는 것은 그 이전번째수가 단독으로 쓰인경우 + 묶인경우가 된다.
        // 현재수를 앞수와 묶어서 두자리수로 생각하는 경우는 앞수가 단독으로 쓰인경우만 해당된다.
        int[][] dp = new int[n+1][3];
        int prev = (arr[0] * 10);
        dp[1][0] = 1; // 첫번째 수를 단독으로 쓰는경우는 1가지 뿐

        for(int i = 2;i<n+1;i++) {
            int pNum = arr[i - 1];
            if (pNum == 0) {
                if (prev + pNum <= 34) {
                    dp[i][1] = dp[i-1][0];
                }
                continue;
            }
            if (prev + pNum <= 34) {
                dp[i][0] = dp[i-1][1] + dp[i-1][0];
                dp[i][1] = dp[i-1][0];
            } else {
                dp[i][0] = dp[i-1][1] + dp[i-1][0];
            }
            prev = pNum * 10;
        }
        System.out.print(dp[n][0] + dp[n][1]);
    }
    static void dfs(int depth) {

        if(depth == n) {
            cnt++;
            return;
        }

        if (arr[depth] <= 34 && arr[depth] > 0) {
            dfs(depth+1);
        }

        if (arr[depth] != 0 && depth+1 < n && arr[depth] * 10 + arr[depth+1] <= 34 && arr[depth] * 10 + arr[depth+1] > 0) {
            // 숫자 하나를 잡았는데, 다음 숫자가 있고, 2자리수를 잡았을때 34 이하인 경우 묶음으로 처리할 수 잇음
            // 그런데 10의 자리 숫자가 0인경우에 이상하게 계산됨

            // 반례 10111
            // ans = 3
            // wrong = 5
            dfs(depth+2);
        }
    }
}