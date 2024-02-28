import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int [] arr;
    static int [] brr;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        brr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0;i<n;i++) arr[i]= Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0;i<n;i++) brr[i]= Integer.parseInt(stk.nextToken());
        // 최장 증가부분 수열 문제
        // 수열을 먼저 정리해야 한다.
        // brr에 대하여 역 인덱싱을 해야한다.
        int [] seq = new int[n];
        int [] rev = new int[n];
        for(int i = 0;i<n;i++){
            rev[brr[i]-1] = i;
        }
        for(int i = 0;i<n;i++){
            seq[i] = rev[arr[i]-1];
        }
        //System.out.println("arr : "+Arrays.toString(arr));
        //System.out.println("brr : "+Arrays.toString(brr));
        int [] candi = new int[n];
        //System.out.println("seq : "+Arrays.toString(seq));
        Arrays.fill(candi, -1);
        int [] dp = new int[n];
        dp[0] = seq[0];
        candi[0] = 0;
        int LIS = 0;
        for(int i= 1;i<n;i++){
            if(dp[LIS] < seq[i]){
                dp[++LIS] = seq[i];
                candi[i] = LIS;
            }else{
                int idx = lower(dp, 0, LIS, seq[i]);
                candi[i] = idx;
                dp[idx] = seq[i];
            }
        }
        //System.out.println("dp : "+Arrays.toString(dp));
        //System.out.println("candi : "+Arrays.toString(candi));
        Stack<Integer> stack = new Stack<>();
        int t = LIS;
        while(t > -1){
            for(int i = arr.length-1;i>-1;i--){
                if(candi[i] != -1 && t == candi[i]){
                    //System.out.println("idx : "+i);
                    stack.add(i);
                    --t;
                }
            }
        }
        int [] res = new int[stack.size()];
        int id = 0;
        while(!stack.isEmpty()){
            res[id] = arr[stack.pop()];
            id++;
        }
        //System.out.println("res : "+Arrays.toString(res));
        Arrays.sort(res);
        //System.out.println("sorted res : "+Arrays.toString(res));
        StringBuilder sb = new StringBuilder();
        sb.append(LIS+1).append("\n");
        for(int i= 0;i<=LIS;i++){
            sb.append(res[i]).append(" ");
        }
        System.out.print(sb);

    }
    static int lower(int [] arr, int start, int end, int find){
        int min = end;
        while(start <= end){
            int mid = (start + end)/2;
            if(arr[mid] < find){
                start = mid+1;
            }else{
                end = mid-1;
                min = Math.min(min, mid);
            }
        }
        return min;
    }
}