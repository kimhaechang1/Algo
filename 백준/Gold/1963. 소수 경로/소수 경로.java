import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int a, b;
    static int[] arr;
    static boolean[] isPrime;
    static int[] v;
    public static void main(String[] args) throws Exception{
        bf = new BufferedReader(new InputStreamReader(System.in));
        TC();
    }
    static void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());
    }

    static void TC() throws Exception{
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        isPrime = new boolean[10000];
        getEra();
        while(T-- > 0) {
            input();
            testCase(sb);
        }
        System.out.print(sb);
    }

    static void testCase(StringBuilder sb) {
        bfs(a, b);
        sb.append(v[b] == 987654321 ? "Impossible" : v[b]).append("\n");
    }
    static void bfs(int number, int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        v = new int[10000];
        Arrays.fill(v, 987654321);
        v[number] = 0;
        queue.add(new int[]{number, v[number]});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if(now[0] == target) {
                break;
            }
            int[] arr = getNumbers(now[0]);
            int cheon = arr[0] * 1000;
            int back = arr[1] * 100;
            int sip = arr[2] * 10;
            int il = arr[3];

            int next;
            for(int i = 1;i<=9;i++) {
                next = i * 1000 + back + sip + il;
                if (v[next] <= now[1] + 1 || isPrime[next]) continue;
                v[next] = now[1] + 1;
                queue.add(new int[]{next, now[1] + 1});
            }

            for(int i = 0;i<=9;i++) {
                next = cheon + (i * 100) + sip + il;
                if (v[next] <= now[1] + 1 || isPrime[next]) continue;
                v[next] = now[1] + 1;
                queue.add(new int[]{next, now[1] + 1});
            }

            for(int i = 0;i<=9;i++) {
                next = cheon + back + (i * 10) + il;
                if (v[next] <= now[1] + 1 || isPrime[next]) continue;
                v[next] = now[1] + 1;
                queue.add(new int[]{next, now[1] + 1});
            }

            for(int i = 0;i<=9;i++) {
                next = cheon + back + sip + i;
                if (v[next] <= now[1] + 1 || isPrime[next]) continue;
                v[next] = now[1] + 1;
                queue.add(new int[]{next, now[1] + 1});
            }
        }
    }
    static int[] getNumbers(int nums) {
        int[] ans = new int[4];
        char[] chs = String.valueOf(nums).toCharArray();
        for(int i = 0;i<4;i++) {
            ans[i] = chs[i] - '0';
        }
        return ans;
    }
    static void getEra() {

        isPrime[0] = isPrime[1] = true;
        for(int i = 2;i<=(int)Math.sqrt(10000);i++) {
            if (!isPrime[i]) {
                for(int j = i + i;j<10000;j += i) {
                    isPrime[j] = true;
                }
            }
        }
    }

    static void solve() {

    }

}