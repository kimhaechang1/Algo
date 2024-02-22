import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    public static void main(String []args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t= 1;t<=T;t++){
            stk = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            int [] arr = new int[n];
            int [] brr = new int[m];
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0;i<n;i++){
                arr[i] = Integer.parseInt(stk.nextToken());
            }
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0;i<m;i++){
                brr[i] = Integer.parseInt(stk.nextToken());
            }
            // 1 1 3 7 8
            // 1 3 6
            Arrays.sort(arr);
            Arrays.sort(brr);
            int s = 0;
            int pointer = n-1;
            for(int i = m-1;i>-1;i--) {
                while (pointer > -1 && brr[i] < arr[pointer]) pointer--;
                s += (n - 1) - pointer;
            }
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}