import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;

    static int n;
    static int m;

    static int[] arr;
    static int[] mrr;
    static long[] S;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(bf.readLine());
        arr = new int[n+1];
        for(int i =1;i<n+1;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        mrr = new int[m];
        for(int i= 0;i<m;i++) {
            mrr[i] = Integer.parseInt(bf.readLine());
        }

        S = new long[n+1];
        for(int i= 1;i<n+1;i++) {
            S[i] = S[i-1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int m: mrr) {
            if (S[n] <= m) sb.append(n).append("\n");
            else sb.append(search(m)).append("\n");
        }
        System.out.print(sb);
    }
    static int search(int target) {
        int s = 1;
        int e = n+1;
        int res = e;
        while(s <= e) {
            int mid = (s + e) / 2;
            if (S[mid] > target) {
                e = mid - 1;
                res = Math.min(mid-1, res);
            } else {

                s = mid + 1;
            }
        }
        return res;
    }


}