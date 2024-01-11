import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int [][] data;
    static int [] minData;
    static StringTokenizer stk;
    static boolean [] sel;
    static int [] rnums;
    static int minCost;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        data = new int[n+1][5];
        minData = new int[5];

        stk = new StringTokenizer(bf.readLine());
        for(int i=0;i<4;i++){
            minData[i] = Integer.parseInt(stk.nextToken());
        }
        for(int i = 1;i<n+1;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0;j<5;j++){
                data[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        rnums = new int[n];
        minCost = Integer.MAX_VALUE;
        sel = new boolean[n+1];
        dfs(1, 0, 0, 0, 0, 0);
        StringBuilder sb = new StringBuilder();
        if(minCost == Integer.MAX_VALUE){
            sb.append(-1);
        }else{
            sb.append(minCost).append("\n");
            for(int i = 0;i<n;i++){
                if(rnums[i] == 0) continue;
                sb.append(rnums[i]).append(" ");
            }
        }
        System.out.print(sb);
    }
    static void dfs(int depth, int p, int f, int s, int v, int c){
        if(depth == n+1){
            if(p < minData[0] || f < minData[1] || s < minData[2] || v < minData[3]){
                return;
            }
            //System.out.println(Arrays.toString(sel));
            //System.out.println("p : "+p +" f : "+f +" s : "+s +" v : "+v +" c : "+c);
            int [] nums = makeNumber(sel);
            //System.out.println(Arrays.toString(rnums));
            if(minCost > c){
                minCost = c;
                rnums = nums.clone();
            }else if(minCost == c){
                boolean isFind = false;
                for(int i = 0;i<n;i++){
                    if(rnums[i] == 0) break;
                    if(nums[i] < rnums[i]){
                        isFind = true;
                        break;
                    }else if(nums[i] > rnums[i]){
                        break;
                    }
                }
                if(isFind){
                    rnums = nums.clone();
                }
            }
            //System.out.println(Arrays.toString(nums));
            //System.out.println("minCost : "+minCost);
            return;
        }

        sel[depth] = true;
        dfs(depth+1, p + data[depth][0], f + data[depth][1], s + data[depth][2], v + data[depth][3], c + data[depth][4]);
        sel[depth] = false;
        dfs(depth+1, p, f, s, v, c);
    }
    static int [] makeNumber(boolean [] s){
        int [] nums = new int[n+1];
        int idx = 0;
        for(int i = 1;i<s.length;i++){
            if(s[i]){
                nums[idx++] = i;
            }
        }
        return nums;
    }
}