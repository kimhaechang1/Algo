import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer stk;
    static int n;
    static int m;
    static int[] points;
    static int[][] lines;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        points = new int[n];
        lines = new int[m][2];
        stk = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            points[i] = Integer.parseInt(stk.nextToken());
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            lines[i][0] = Integer.parseInt(stk.nextToken());
            lines[i][1] = Integer.parseInt(stk.nextToken());

        }
        // 점이 몇개의선분에 겹쳐있는가 가 아니라
        // 선분이 몇개에 점과 겹쳐있는가 이므로
        // 각 시작점과 끝점이 점 인덱스에 상한선 하한선 경계를 구해야한다. -> 이분탐색
        Arrays.sort(points);
        StringBuilder sb = new StringBuilder();
        for(int i= 0;i<lines.length;i++){
            int s = lines[i][0];
            int e = lines[i][1];
            int start = 0;
            int end = points.length-1;
            while(start <= end){
                int mid = (start + end) / 2;
                if(points[mid] >= s){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            int lb = start;
            start = 0;
            end = points.length-1;
            while(start <= end){
                int mid = (start + end) / 2;
                if(points[mid] > e){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            int ub = end+1;
            sb.append(ub - lb).append("\n");

        }
        System.out.print(sb);
    }
}
