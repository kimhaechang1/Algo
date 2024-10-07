import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static String[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new String[n];
        for(int i= 0;i<n;i++) {
            arr[i] = bf.readLine();
        }

        HashMap<String, Integer> suffixCnt = new HashMap<>();

        // 접미사 중에서 말이지 짝수번 나오는건 죽고, 홀수번 나오는건 산다.
        for(int i = 0;i<arr.length;i++) {
            for(int j = arr[i].length()-1;j > -1 ;j-- ){
                String suffix = arr[i].substring(j);
                suffixCnt.put(suffix, suffixCnt.get(suffix) == null ? 1 : suffixCnt.get(suffix) + 1);
            }
        }
        int ans = 0;
        for(String suffix: suffixCnt.keySet()) {
            if ((suffixCnt.get(suffix) & 1) == 1) ans++;
        }
        System.out.println(ans);

    }
}