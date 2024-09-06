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
        int max = 0;
        long sum = 0;
        for(int i = 0;i<n;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            if(max < arr[i]) max = arr[i];
            sum += arr[i];
        }
        long s = max;
        long e = sum;
        long ans = sum;
        while(s <= e) {
            long mid = (s + e) /2;
            if (search(mid)) {
                // 녹화 가능한 길이를 늘리면 블루레이의 총 개수가 줄어든다.
                s = mid + 1;
            } else {
                e = mid - 1;
                ans = Math.min(ans, mid);
            }
        }
        System.out.print(ans);
    }
    static boolean search(long value) {
        long cnt = 0;
        long sum = 0;
        for(int i = 0;i<arr.length;i++) {
            if ( sum + arr[i] > value) {
                cnt++;
                sum = 0;
            }
            sum += arr[i];
        }
        if(sum > 0) {
            cnt++;
        }
        return cnt > m;
    }
}