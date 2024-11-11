import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static int[] arr;
    static BufferedReader bf;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    public static void input() throws Exception{
        bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }
    static void solve() {
        // 처음에 0에 다 있음
        //세준이는 한 걸음에 좌표 하나씩 감
        // 모든책을 놔둔 후 다시 되돌아 올 필요는 없음
        // -1 3 4 5 6 11
        // 2 + 6 + 10 + 11 ?
        // -39 -37 -29 -28 -6 2 11
        // 문득 드는 생각
        // 음수부랑 양수부를 나눠서
        // 큰거부터 하나뽑으면 절댓값 씌워서 더하고 m -1개 더뽑고 이런식으로 진행하면 되는거 아님?
        // 그리고 젤 큰값 빼면 될듯

        TreeSet<Integer> lower = new TreeSet<>();
        TreeSet<Integer> upper = new TreeSet<>();

        for(int i = 0;i<arr.length;i++) {
            if (arr[i] > 0) upper.add(arr[i]);
            else lower.add(arr[i]);
        }
        int max = Math.max(upper.size() == 0 ? 0 : upper.last(), Math.abs(lower.size() == 0 ? 0 : lower.first()));

        int sum = 0;
        while(!lower.isEmpty()) {
            sum += Math.abs(lower.pollFirst()) * 2;
            int cnt = m - 1;
            while(cnt-- > 0 && !lower.isEmpty()) lower.pollFirst();
            if (lower.isEmpty()) break;
        }
        while(!upper.isEmpty()) {
            sum += Math.abs(upper.pollLast()) * 2;
            int cnt = m - 1;
            while(cnt-- > 0 && !upper.isEmpty()) upper.pollLast();
            if (upper.isEmpty()) break;
        }
        System.out.print(sum - max);
    }

}