import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static int[][] info;
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb =new StringBuilder();
        while(T-- > 0) {
            n = Integer.parseInt(bf.readLine());
            info = new int[n][2];
            HashSet<Integer> xx = new HashSet<>();
            HashSet<Integer> yy = new HashSet<>();
            for(int i = 0;i<n;i++) {
                stk = new StringTokenizer(bf.readLine());
                info[i][0] = Integer.parseInt(stk.nextToken());
                info[i][1] = Integer.parseInt(stk.nextToken());
                xx.add(info[i][0]);
                yy.add(info[i][1]);
            }
            // adhoc 이었음
            // 이유: 가로등이 균형잡힐라면 x좌표가 같으면 상관없지만, 다르면 y집합이 같아야 한다.
            // 즉, 모든 서로다른 x쌍에 대하여 서로다른 y좌표들의 종류가 존재할 때
            // 어짜피 x좌표들이 서로 다르다면서 y좌표 집합이 같은것들이 존재한다면
            // 이들은 x로만 구분되므로 x * y.size() 가 전체 좌표들의 개수가 된다.
            // 즉, 집합연산 후  x.size() * y.size() == n 이라면 balanced가 된다.

            if (xx.size() * yy.size() == n) {
                sb.append("BALANCED").append("\n");
            } else {
                sb.append("NOT BALANCED").append("\n");
            }
        }
        System.out.print(sb);
    }
}