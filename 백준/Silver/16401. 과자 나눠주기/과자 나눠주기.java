import java.util.*;
import java.io.*;

public class Main {
    static int M;
    static int N;
    static int [] arr;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        arr = new int[N];
        int sum = 0;
        for(int i = 0;i<N;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            sum += arr[i];
        }
        Arrays.sort(arr);
        // 파라메트릭 서치
        int s = 1;
        int e = 1000000000;
        int answer = 1000000000;
        while(s <= e) {
            int mid = (s+e)/2;
            if(chk(mid)) {
                s = mid+1;
            }else {
                e = mid-1;
            }
        }
        System.out.println(e);

    }
    static boolean chk(int mid) {
        int c = 0;
        for(int i = 0;i<N;i++) {
            if(arr[i] >= mid) {
                c+=arr[i]/mid;
            }
            if(c >= M) return true;
        }
        return false;
    }

}
