import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static int[][] map;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][2];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            map[i][0] = Integer.parseInt(stk.nextToken());
            map[i][1] = Integer.parseInt(stk.nextToken());
        }
        // index 0 p, index 1 d
        boolean [] used = new boolean[10002];
        int ans = 0;
        Arrays.sort(map, (a, b)->{
            if(b[1] == a[1]) {
                return Integer.compare(b[0], a[0]);
            }
           return Integer.compare(b[1], a[1]);
        });
        //System.out.println(Arrays.deepToString(map));
        for(int time = 10000;time > 0; time--) {
            boolean find = false;
            int maxIdx = -1;
            int max = 0;
            for(int i = 0;i<map.length;i++) {
                if(used[i]) continue;
                if(map[i][1] >= time && max < map[i][0]){
                    maxIdx = i;
                    max = map[i][0];
                }
            }
            if(maxIdx != -1) {
                used[maxIdx]= true;
                ans += map[maxIdx][0];
            }
        }

        System.out.print(ans);
    }
}