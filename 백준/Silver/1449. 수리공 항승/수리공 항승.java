import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int l;
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        l = Integer.parseInt(stk.nextToken());
        --l;
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0;i<n;i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);
        int idx = 1;
        int cnt = 1;
        int s = arr[0];
        for(;idx<n;idx++){
            if(arr[idx] <= s + l){
                continue;
            }
            s = arr[idx];
            cnt++;
        }
        System.out.print(cnt);
    }
}