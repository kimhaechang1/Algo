import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int T;
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            n = Integer.parseInt(bf.readLine());
            int[] rank = new int[n+1];
            for(int i= 0;i<n;i++){
                stk = new StringTokenizer(bf.readLine());
                int r1 = Integer.parseInt(stk.nextToken());
                int r2 = Integer.parseInt(stk.nextToken());
                rank[r1] = r2;

            }

            // 정답을 구하기 위해서는 한쪽만 오름차순이면 반대쪽은 상관없으므로
            // 한쪽 기준으로 오름차순을 해놓자.
            // 한쪽을 기준으로 오름차순, 반대쪽을 기준으로 내림차순 정렬

            // 별도의 풀이 추가
            // 어짜피 한쪽만 검사할거라면 반대쪽은 계수정렬을 해도 되지않을까
            // 왜냐하면 절대 겹치는 등수가없다고 했으므로
            int min = rank[1];
            int cnt = 1;
            for(int i = 2;i<n+1;i++){
                if(min > rank[i]) {
                    min = rank[i];
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}