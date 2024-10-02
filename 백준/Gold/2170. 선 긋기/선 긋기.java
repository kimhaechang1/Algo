import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int[][] info;
    static int n;
    static final int NULL = -1_111_111_111;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        info = new int[n][2];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(info, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int s = info[0][0];
        int e = info[0][1];
        long sum = 0;
        for(int i = 1;i<n;i++) {
            if (e >= info[i][0] && e < info[i][1]) {
                e = Math.max(info[i][1], e);
                continue;
            }
            if (e < info[i][0]) {
                sum += (e - s);
                s = info[i][0];
                e = info[i][1];
            }
        }
        sum += (e - s);
        System.out.print(sum);
    }
}