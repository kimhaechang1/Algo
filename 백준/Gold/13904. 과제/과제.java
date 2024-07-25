import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static class Data implements Comparable<Data>{
        int d;
        int w;

        public Data(int d, int w){
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Data o) {
            if(this.d == o.d){
                return this.w - o.w;
            }
            return this.d - o.d;
        }

        public String toString(){
            return "[ dday: "+d+" worth: "+w+"]";
        }
    }
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        // 크기가 얼마안큼 N^2까지 충분히 가능
        // 그러면 maxDay에서부터 날짜를 하나씩 감소하면서, 그 날짜보다 여유로운 아직 처리안된 과제들 중에 하나를 고른다.
        Data[] drr = new Data[n];
        int maxDay = 1;
        for(int i = 0;i<n;i++){
            stk =new StringTokenizer(bf.readLine());
            drr[i] = new Data(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            maxDay = Math.max(maxDay, drr[i].d);
        }
        boolean[] v = new boolean[n];
        int ans = 0;
        for(int i = maxDay;i>0;i--){
            int maxW = 0;
            int mIdx = 0;
            boolean find = false;
            for(int j = 0;j<n;j++){
                if(v[j]) continue;
                if(drr[j].d >= i && drr[j].w > maxW){
                    find = true;
                    maxW = drr[j].w;
                    mIdx = j;
                }
            }
            if(find){
                v[mIdx] = true;
                ans += maxW;
            }
        }
        System.out.println(ans);


    }
}