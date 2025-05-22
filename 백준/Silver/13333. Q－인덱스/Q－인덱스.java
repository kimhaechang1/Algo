import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[] arr;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();

    }

    void input() throws Exception {

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }

    void solve() {

        // 가장 많이 인용된 논문의 개수, 그 논문들의 인용횟수를 다음과 같이 정의한다.
        // 0번 논문을 8번, 1번 논문을 4번, 2번 논문을 5번, 3번 논문을 3번, 4번 논문을 10번 인용함

        // 즉, arr[1 ~ N] 의 원소값들 중에서 k 이상인 값이 k개 이상 존재하고, 그 나머지 arr[1 ~ N - k] 가 k 이하여야 한다.
        // 3 4 5 8 10
        // 다시 생각해보자.
        // 정렬된 상태에서 이분탐색으로 q 인덱스 하나를 결정지어서
        // 그 값의 validation에 따라 나누는건 어떨까
        // 만약 가능한 q 인덱스가 나오면 스탑하면 된다.
        // 만약 k 번 이상 인용된 논문이 k보다 작으면 더 큰값으로 잡으면 된다.
        // 5 7 8 9 10 12
        // 어짜피 k번이상 인용된 논문이 k개 이상있으면, n - k의 경우에서는 k개 이하의 개수가 n - k보다 더 많다고 해도 상관없다.
        int s = 0;
        int e = 10001;
        int ans = 0;
        while(s <= e) {
            int mid = (s + e) / 2;
            int cnt = 0;
            int cnt2 = 0;
            for(int i = 0; i < n; i++) {
                if (arr[i] >= mid) {
                    cnt++;
                }
                if (arr[i] <= mid){
                    cnt2++;
                }
            }
            if (cnt >= mid && arr.length - mid <= cnt2) {
                ans = mid;
                break;
            }

            if (cnt < mid) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.print(ans);

    }

}