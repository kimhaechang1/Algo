import java.util.*;
import java.io.*;
public class Main {
    static int [] nums;
    static char [] arr;
    static char [] res;
    static StringTokenizer stk;
    static char [] expr = {'+','-','*','/'};
    static int N;
    static int min;
    static int max;
    static boolean [] v;
    public static void main(String[] args) throws Exception{
        // 연산자의 순서가 중요하다. -> 순열
        // 중복된 원소가 존재한다 -> 선택한 결과가 똑같으면 안됨 -> prev 체크
        // 연산자의 개수만큼 arr배열을 만들고 순열의 결과 res배열을 만들어서
        // 연산자를 끼워넣고 연산한 결과를 최솟값 최대값 갱신한다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nums = new int[N];
        stk =new StringTokenizer(bf.readLine());
        for(int i= 0;i<N;i++){
            nums[i] = Integer.parseInt(stk.nextToken());
        }
        int [] count = new int[4];
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0;i<4;i++){
            count[i] = Integer.parseInt(stk.nextToken());
        }
        arr = new char[N-1];
        res = new char[N-1];
        v = new boolean[N-1];
        int idx = 0;
        for(int i= 0;i<4;i++){
            while(count[i]-- >0){
                arr[idx++] = expr[i];
            }
        }
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        dfs(0);
        System.out.println(max);
        System.out.print(min);
    }
    static void dfs(int depth){
        if(depth == N-1){
            int [] t = new int[nums.length];
            System.arraycopy(nums, 0, t, 0,N);
            for(int i= 0;i<N-1;i++){
                if(res[i] == '+') {
                    t[i+1] = (t[i]+t[i+1]);
                }else if(res[i] == '-'){
                    t[i+1] = (t[i]-t[i+1]);
                }else if(res[i] == '*'){
                    t[i+1] = (t[i]*t[i+1]);
                }else if(res[i] == '/'){
                    t[i+1] = (t[i]/t[i+1]);
                }
            }
            if(max < t[N-1]){
                max = t[N-1];
            }
            if(min > t[N-1]){
                min = t[N-1];
            }
            return;
        }
        char prev = '.';
        for(int i= 0;i<N-1;i++){
            if(prev == arr[i]) continue;
            if(!v[i]){
                v[i] = true;
                res[depth] = arr[i];
                prev = res[depth];
                dfs(depth+1);
                v[i]= false;
            }
        }

    }
}