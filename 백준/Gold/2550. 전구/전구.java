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
        // LIS를 돌리고 역추적을 위해 후보 배열이 필요하다
        // 또한 LIS돌릴때, 최소 하나의 원소를 넣고 시작하기 때문에 해당 원소의 후보값을 안넣어두면 에러가 발생할 수 있다.
        int [] seq = new int[n];
        int [] rev = new int[n];
        for(int i = 0;i<n;i++){
            rev[brr[i]-1] = i;
        }
        for(int i = 0;i<n;i++){
            seq[i] = rev[arr[i]-1];
        }
        int [] candi = new int[n];
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
        Stack<Integer> stack = new Stack<>();
        int t = LIS;
        while(t > -1){
            for(int i = arr.length-1;i>-1;i--){
                if(candi[i] != -1 && t == candi[i]){
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
        Arrays.sort(res);
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