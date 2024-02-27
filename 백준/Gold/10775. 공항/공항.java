import java.util.*;
import java.io.*;

public class Main{
    static int gn;
    static int pn;
    static int [] gates;
    static int [] p;
    static void init(){
        p = new int[gn+1];
        for(int i= 0;i<gn+1;i++){
            p[i] = i;
        }
    }
    static int find(int x){
        if(p[x] == x) return p[x];
        else return p[x] = find(p[x]);
    }
    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b){
            return false;
        }
        p[b] = a;
        return true;
    }

    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        gn = Integer.parseInt(bf.readLine());
        pn = Integer.parseInt(bf.readLine());
        gates = new int[pn];
        for(int i = 0;i<pn;i++){
            gates[i] = Integer.parseInt(bf.readLine());
            // 1 2 3 4 .... G
            // 1~g[i] 쑤셔넣는데
            // 만약 현재 번째의 비행기가 쑤셔들어갈 수 없다면 공항 폐쇄로 아무도 못들어간다
            // 이미 자리를 꿰차고 있는데 매번 뒤에서부터 검사해야하는 불필요성이 생긴다.
            // 이걸 줄여야 한다.

        }
        init();
        int cnt = 0;
        for(int i = 0;i<pn;i++){
            int parent = find(gates[i]);
            if(parent != 0){
                cnt++;
                union(parent-1, parent);
            }else{
                break;
            }
        }
        System.out.print(cnt);
    }
}