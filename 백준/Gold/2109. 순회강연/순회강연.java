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
        // 먼저 먹을 수 있는 강연부터 먹는걸 고려해보자.
        // 일반적인 그리디한 생각이라면 큰거부터 먹을려 하는데, 이 문제는 어떤 날짜안에만 수행하면 그만이다.
        // 그말은 즉슨, 한계치까지 날짜가 차져있을 때, 그 날짜에서 먹을수 있는 최대한을 구해야 하는것
        if(n == 0){
            System.out.print(0);
            return;
        }
        boolean [] used = new boolean[10002];
        int ans = 0;
        Arrays.sort(map, (a, b)->{
            if(b[1] == a[1]) {
                return Integer.compare(b[0], a[0]);
            }
           return Integer.compare(b[1], a[1]);
        });
        for(int time = 10000;time > 0; time--) {
            int maxIdx = -1;
            int max = 0;
            if(map[0][1] < time) continue;
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