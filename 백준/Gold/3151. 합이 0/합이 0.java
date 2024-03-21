import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int [] arr;
    static StringTokenizer stk;
    static class Data implements Comparable<Data>{
        int idx;
        long value;
        public Data(int idx, long value){
            this.idx = idx;
            this.value = value;
        }
        @Override
        public int compareTo(Data o) {
            return Long.compare(this.value, o.value);
        }

        @Override
        public String toString() {
            return "[ idx : "+idx+" value : "+value+" ]";
        }
    }
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        stk = new StringTokenizer(bf.readLine());
        arr = new int[n];
        Data [] drr = new Data[n];
        for(int i= 0;i<n;i++){
            arr[i] = Integer.parseInt(stk.nextToken());
            drr[i] = new Data(i, arr[i]);
        }
        Arrays.sort(drr);
        long cnt = 0;
        for(int i = 0;i<n;i++){
            if(drr[i].value > 0) break;
            int s = i+1;
            int e = n-1;
            while(s < e){
                long sum = drr[i].value + drr[s].value+drr[e].value;
                if(sum == 0){
                    long startCnt = 1;
                    long endCnt = 1;
                    if(drr[s].value == drr[e].value){
                        // s 번재부터 e번째까지 모두 같기 때문에
                        // s ~ e까지의 개수중에서 2개를 뽑는 경우와 같음
                        long p = e - s + 1;
                        cnt += (p * (p-1))/2;
                        break;
                    }while(drr[s].value == drr[s+1].value){
                        // s의 개수를 뽑고
                        s++;
                        startCnt++;
                    }while(drr[e].value == drr[e-1].value){
                        // e의 개수를 뽑는다.
                        endCnt++;
                        e--;
                    }
                    cnt += (startCnt * endCnt);
                }

                if(sum < 0){
                    s++;
                }else {
                    e--;
                }

            }
        }
        System.out.print(cnt);
    }
}