import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static class Data implements Comparable<Data>{
        int s;
        int e;
        public Data(int s, int e){
            this.s = s;
            this.e = e;
        }
        public int compareTo(Data o){
            if(this.s == o.s){
                return this.e - o.e;
            }
            return this.s - o.s;
        }
    }
    static Data [] datas;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        datas = new Data[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            datas[i] = new Data(s, e);
        }
        // 하나의 끝나는 시간 기준으로 그 사이에 존재하는 시작시간들은 추가적으로 강의실이 필요한 거다.
        // 차곡차곡 넣는 그리디 문제는 pq를 사용하며, 특정 기준점을 정하고 그 안에 집어넣는식으로 진행해야 한다.
        // 기준점이 끝나는시간으로 정했으면, pq에 다시 넣는조건이 있는지 파악해야한다.
        // pq에 다시 넣는다는것은 고려대상이란 의미 이므로, 해당 데이터를 다시 기준점으로 정할 필요가 있는지 생각해야 한다.
        // 여기서는 어떤 강의가 시작하고 끝나는 사이에 시작해버리는 강의가 있을 때, 해당 강의의 끝나는 지점에서도 다시 고려해야하므로
        // 특정 끝나는시간 기준으로 그 사이에 시작하는것은 다시 고려해야하므로 pq에 넣고,
        // 기준 끝나는 시간보다 더 큰 시작시간을 가진 데이터가 존재하면, 이전 기준점은 의미가 없어지므로 기준점을 바꾼다.
        Arrays.sort(datas, (a, b)->{
           if(a.s == b.s){
               return a.e - b.e;
           }
           return a.s - b.s;
        });
        pq.add(datas[0].e);
        for(int i= 1;i<n;i++){
            if(pq.peek() <= datas[i].s){
//                System.out.println(tset.first()+" 값은 "+datas[i].s+" 보다 작아졌으므로 기준점을 교체함");
                pq.poll();
            }
            pq.add(datas[i].e);

//            System.out.println(datas[i].s+" "+datas[i].e+" 는 재 고려 대상임");
        }
        System.out.print(pq.size());
    }
}