import java.io.*;
import java.util.*;

public class Main{
    static int n, m;
    static int T;
    static int[] arr, brr;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder s = new StringBuilder();
        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            n = Integer.parseInt(bf.readLine());
            arr = new int[n];
            stk = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }
            m = Integer.parseInt(bf.readLine());
            brr = new int[m];
            stk = new StringTokenizer(bf.readLine());
            for (int i = 0; i < m; i++) {
                brr[i] = Integer.parseInt(stk.nextToken());
            }
            Arrays.sort(arr);
            for (int i = 0;i<m;i++) {
                if(search(brr[i])){
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            }

            s.append(sb.toString());
        }
        System.out.print(s);
    }
    static boolean search(int find) {
        int s = 0;
        int e = arr.length-1;
        while(s <= e) {
            int mid = (s + e) / 2;
            if(arr[mid] == find) {
                return true;
            } else if (arr[mid] > find) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return false;
    }
}