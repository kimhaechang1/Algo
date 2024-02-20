import java.util.*;
import java.io.*;

public class Main{
    static long [] X;
    static long [] A;
    static int n;
    static StringTokenizer stk;
    static class Data{
        int x;
        int a;
        public Data(int x, int a){
            this.x = x;
            this.a = a;
        }
    }
    static Data [] drr;
    public static void main(String [] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        X = new long[n];
        A = new long[n];
        drr = new Data[n];
        for(int i = 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            drr[i] = new Data(x, a);

        }
        // 그리디 정렬이었다.
        // 거리에 매몰되면 풀기 힘듬
        // 사실상 거리는 어디가 되던지 간에 거리의 차의 합은 동일함!!
        // => 그럼 결론으론 사람수에 따라 달라지는것
        /*
        * 누적합 + 이분탐색으로도 풀리는거라고 한다.
        * 사람별 거리의 합이 최소다 -> 거리는 모두 동일하게 나온다 -> 결국 사람수가 중요
        * 여기서 알 수 있는점 중에 하나는 임계지점은 결국 더이상 인구수의 차이가 최소일때임을 주목
        * 결국 구해야하는건 인구수 차이가 최소가 되었을 때에 위치값
        * 여기서 말하는 인구수는 누적된 인구를 의미
        * 왜냐하면 차가 가장 작다는것은 마지막으로 이득을 볼 위치라는 것
        * */
        Arrays.sort(drr, (a, b)->{
            return a.x - b.x;
        });
        long [] sum = new long[n];
        sum[0] = drr[0].a;
        for(int i = 1;i<n;i++){
            sum[i] = sum[i-1] + drr[i].a;
        }

        int s = 0;
        int e = n-1;
        long minValue = Long.MAX_VALUE;
        while(s <= e){
            int mid = (s+e)/2;
            if(sum[mid] >= sum[n-1] - sum[mid]){
                // 임의의 중간까지의 합이 그 중간 이후의 누적합보다 크거나 같다면
                e = mid-1;
                minValue = Math.min(minValue, drr[mid].x);
            }else{
                s = mid+1;
            }
        }
        System.out.println(minValue);
    }
}