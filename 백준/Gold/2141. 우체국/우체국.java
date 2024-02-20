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
        long sum = 0;
        for(int i = 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            drr[i] = new Data(x, a);
            sum += a;
        }
        // 그리디 정렬이었다.
        // 거리에 매몰되면 풀기 힘듬
        // 사실상 거리는 어디가 되던지 간에 거리의 차의 합은 동일함!!
        // => 그럼 결론으론 사람수에 따라 달라지는것
        // 단순하게 생각하면 좌우의 사람의 수가 균형이 동일해지는 지점에 우체국을 설치해야 함
        // 이런 중앙값 찾기 할 때, 차례차례 넣어주다가 전체의 절반을 넘어서는 순간 종료

        Arrays.sort(drr, (a, b)->{
            return a.x - b.x;
        });
        long result = 0;
        for(int i = 0;i<n;i++){
            result += drr[i].a;
            if((sum+1)/2 <= result){
                System.out.println(drr[i].x);
                break;
            }
        }
    }
}