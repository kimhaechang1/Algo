import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static class Data implements Comparable<Data>{
        int idx;
        int val;
        public Data(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
        public int compareTo(Data o){
            if(this.val == o.val){
                return o.idx - this.idx;
            }
            return o.val - this.val;
        }
    }

    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Data> [] rec1 = new TreeSet[101];
        for(int i= 0;i<rec1.length;i++){
            rec1[i] = new TreeSet<>();
        }
        HashMap<Integer, int[]> cache = new HashMap<>();
        TreeSet<Data> rec23 = new TreeSet<>();
        int n = Integer.parseInt(bf.readLine());
        for(int i = 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(stk.nextToken());
            int val = Integer.parseInt(stk.nextToken());
            int g = Integer.parseInt(stk.nextToken());
            rec1[g].add(new Data(idx, val));
            rec23.add(new Data(idx, val));
            cache.put(idx, new int[]{g, val});
        }
        int m = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(m-- > 0){
            stk = new StringTokenizer(bf.readLine());
            String cmd = stk.nextToken();
            int idx;
            int val;
            int g;
            Data data;
            int opt;
            int res;
            switch(cmd){
                case "recommend" :
                    g = Integer.parseInt(stk.nextToken());
                    opt = Integer.parseInt(stk.nextToken());
                    if(opt == 1){
                        data = rec1[g].first();
                    }else{
                        data = rec1[g].last();
                    }
                    sb.append(data.idx).append("\n");
                    break;
                case "recommend2":
                    opt = Integer.parseInt(stk.nextToken());
                    if(opt == 1){
                        data = rec23.first();
                    }else{
                        data = rec23.last();
                    }
                    sb.append(data.idx).append("\n");
                    break;
                case "recommend3":
                    opt = Integer.parseInt(stk.nextToken());
                    val = Integer.parseInt(stk.nextToken());
                    res = -1;
                    if(opt == 1){
                        data = rec23.lower(new Data(0, val));
                        if(data != null){
                            res = data.idx;
                        }
                    }else{
                        data = rec23.higher(new Data(0, val));
                        if(data != null){
                            if(data.val == val){
                                continue;
                            }
                            res = data.idx;
                        }
                    }
                    sb.append(res).append("\n");
                    break;
                case "solved":
                    idx = Integer.parseInt(stk.nextToken());
                    int [] info = cache.get(idx);
                    cache.remove(idx);
                    rec1[info[0]].remove(new Data(idx, info[1]));
                    rec23.remove(new Data(idx, info[1]));
                    break;
                case "add":
                    idx = Integer.parseInt(stk.nextToken());
                    val = Integer.parseInt(stk.nextToken());
                    g = Integer.parseInt(stk.nextToken());
                    cache.put(idx, new int[]{g, val});
                    rec1[g].add(new Data(idx, val));
                    rec23.add(new Data(idx, val));
                    break;
            }
        }
        System.out.print(sb);


    }
}