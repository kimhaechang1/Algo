import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int[] S = new int[n];
        S[0] = arr[0];
        for(int i = 1;i<n;i++) {
            S[i] = S[i-1] + arr[i];
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = k-1;i<n;i++) {
            if(i - (k - 1) == 0) {
                list.add(S[i]);
                continue;
            }
            list.add(S[i] - S[i - (k - 1) - 1]);
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.print(list.get(0));

    }
}