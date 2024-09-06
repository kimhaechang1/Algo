import java.io.*;
import java.util.*;

public class Main{
    static int n, m;
    static int[] arr;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk  = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr= new int[n];
        stk =new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        long s = 1;
        long e = 2_000_000_000;
        long ans = 2_000_000_000;
        while(s <= e) {
            long mid = (s + e) /2;
            if (search(mid) >= m) {
                // 절단기의 높이를 높이면 나무를 덜 가져가게 된다.
                s = mid + 1;
            } else {
                e = mid - 1;
                ans = Math.min(ans, mid-1);
            }
        }
        System.out.print(ans);
    }
    static long search(long value) {
        long result = 0;
        for(int i = 0;i<arr.length;i++) {
            if(arr[i] <= value) continue;
            result+= (arr[i] - value);
        }
        return result;
    }
}