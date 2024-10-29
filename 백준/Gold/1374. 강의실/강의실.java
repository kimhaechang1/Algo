import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int[][] info;

    public static void solve() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int i = 0;i < n;i++) {
            treeMap.put(info[i][1], treeMap.getOrDefault(info[i][1], 0) + 1);
            treeMap.put(info[i][2], treeMap.getOrDefault(info[i][2], 0) - 1);
        }
        TreeMap<Integer, Integer> lineMap = new TreeMap<>();
        int sum = 0;
        int ans = 0;
        for(Map.Entry<Integer, Integer> treeMapEntry: treeMap.entrySet()) {
            sum += treeMapEntry.getValue();
            int sp = treeMapEntry.getKey();
            lineMap.put(sp, lineMap.getOrDefault(sp, 0) + sum);
            ans = Math.max(lineMap.getOrDefault(sp, 0), ans);
        }
        System.out.println(ans);
    }
    public static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        info = new int[n][3];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            info[i][2] = Integer.parseInt(stk.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}