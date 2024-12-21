import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static int s;
    static BufferedReader bf;
    static StringTokenizer stk;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void testCase() throws Exception {

    }

    void input() throws Exception {
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        s = Integer.parseInt(bf.readLine());
    }

    int TC() {
       return 0;
    }

    void solve() {
        // 연속된 둘을 버블교환이 가능하니까
        // 소트한 결과가 사전순으로 가장 뒷서는 것
        // 사전순으로 가장 뒷설려면, 앞에서부터 무조건 가장 커야한다.


        // 어떠한 인덱스에 위치한 원소가 지정된 위치에 오려면 ( 해당원소 위치 - 옮길위치가 된다 ).
        for(int i = 0; i < n && s > 0;  i++) {
            int changeIdx = -1;
            int maxChange = arr[i];
            for(int j = i + 1; j <= Math.min(n - 1, i + s); j++) {
                if (maxChange < arr[j]) {
                    maxChange = arr[j];
                    changeIdx = j;
                }
            }

            if (changeIdx != -1){
                for(int j = changeIdx - 1; j >= i; j--) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
                s -= (changeIdx - i);
            }
        }


        // 1 2 3 4 5
        // 1 2 3 5 4
        // 1 2 5 3 4
        // 1 5 2 3 4
        // 5 1 2 3 4
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n; i++) sb.append(arr[i]).append(" ");
        System.out.print(sb);
    }

}