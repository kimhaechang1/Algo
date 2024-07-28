import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int T;
    static class Data{
        int s1;
        int s2;

        public Data(int s1, int s2){
            this.s1 = s1;
            this.s2 = s2;
        }
        public String toString(){
            return "[ s1 : "+s1 + " s2: "+s2+ " ]";
        }
    }
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            n = Integer.parseInt(bf.readLine());
            Data [] drr = new Data[n];
            for(int i= 0;i<n;i++){
                stk = new StringTokenizer(bf.readLine());
                drr[i] = new Data(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            }

            // 정답을 구하기 위해서는 한쪽만 오름차순이면 반대쪽은 상관없으므로
            // 한쪽 기준으로 오름차순을 해놓자.
            // 한쪽을 기준으로 오름차순, 반대쪽을 기준으로 내림차순 정렬
            Arrays.sort(drr, (a, b)-> {
               if(b.s1 == a.s1){
                   return b.s2 - a.s2;
               }
               return a.s1 - b.s1;
            });


            int s2Min = drr[0].s2;
            for(int i = 1;i<n;i++){
                if(s2Min > drr[i].s2) {
                    s2Min = Math.min(s2Min, drr[i].s2);
                    // 이러면 적어도 하나만을 만족한다. 왜냐하면 s2의 등수가 더 높은 등급이기 때문
                    continue;
                }
                drr[i].s2 = -1;
            }

            int cnt = 0;
            for(int i = 0;i<n;i++){
                if(drr[i].s2 == -1) continue;
                cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}