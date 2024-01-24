import java.util.*;
import java.io.*;

public class Main{

    static int n;
    static int [][] arr;
    static long l;
    static long p;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr =new int[n][2];
        for(int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
        }
        stk =new StringTokenizer(bf.readLine());
        l = Integer.parseInt(stk.nextToken());
        p = Integer.parseInt(stk.nextToken());

        PriorityQueue<Integer> fuel = new PriorityQueue<>(Collections.reverseOrder()); // 얻을 수 있는 연료에 따른 내림차순
        PriorityQueue<int []> dis = new PriorityQueue<>((a,b)->{
            return a[0] - b[0];
        });

        for(int i= 0;i<n;i++){
            dis.add(arr[i]);
        }
        // 연료가 새어나간다는건 그 주유소까지 가는데 있어서 빠져나간단 의미가 된다.
        // 가까운 주유소부터 멈출 후보가 되고, 해당 주유소까지
        int cnt = 0;

        while(p < l) {
            while (!dis.isEmpty() && dis.peek()[0] <= p) {
                fuel.add(dis.poll()[1]);
            }
            if (fuel.isEmpty()) {
                System.out.println(-1);
                return;
            }
            cnt++;
            p += fuel.poll();

        }
       System.out.println(cnt);


    }
    static void printPQ(PriorityQueue<int[]> pq){
        Iterator<int []> iter = pq.iterator();
        while(iter.hasNext()){
            System.out.println(Arrays.toString(iter.next()));
        }
    }
}