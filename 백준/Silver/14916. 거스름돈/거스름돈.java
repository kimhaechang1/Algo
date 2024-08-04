import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static StringTokenizer stk;
    static int l;
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        // 동전을 하나씩만 쓸 수 있다면 -> knapsack
        // 동전의 개수가 무한정있다면 -> 그리디 접근 가능
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int cnt = 0;
        if(n % 5 == 0){
            cnt += n / 5;
            n = 0;
        }else{
            while(n >= 2 && n % 5 != 0){
                n -= 2;
                cnt++;
            }
            if(n % 5 == 0){
                cnt += (n / 5);
                n %= 5;
            }
        }
        System.out.println(n != 0 ? -1 : cnt);
    }
}