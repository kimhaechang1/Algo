import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer stk;
    static int n;
    static int [][] map;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static int ans = 200;
    public static void main(String[] args) throws Exception {
        // 차를 일정수치로 정해놓고 이분탐색 및 BFS돌려도 될듯
        // 전체 원소가 최악의 경우 10만개 탐색이고
        // 시간제한내로 풀려면 곱하기 최대 1000이다.
        // 일단 0~200사이의 이분탐색 시간복잡도는 약 log200이므로 굉장히 작고
        // 하나의 차를 기준점으로 해당 차가 나올수 있는 모든경우를 파악해야하므로
        // 반복문을 i+mid <= 200일때까지 수행한다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        for (int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0;j<n;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        int s = -1;
        int e = 201;

        while(s <= e){
            int mid = (s + e) / 2;
             // 차를 하나 정해놓고
            // 전체를 돌면서 가능한지 여부를 체크한다.
            // 숫자가 작으니까 가능하단걸 잊지말자
            boolean can = false;
            for(int i = 0;i + mid <= 200;i++){
                int min = i;
                int max = i + mid;
                if(bfs(min, max)){
                    can = true;
                    break;
                }
            }
            if(can){
                e = mid - 1;
                ans = Math.min(mid, ans);
            }else{
                s = mid + 1;
            }

        }
        System.out.print(ans);
    }

    static boolean bfs(int min, int max){
        Queue<int []> queue = new ArrayDeque<>();
        boolean [][] v = new boolean[n][n];
        // 시작점 검사 안함 ㅋㅋ
        if(map[0][0] < min || map[0][0] > max) return false;
        v[0][0] = true;
        queue.add(new int[] {0,0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == n-1 && now[1] == n-1){
                return true;
            }
            for(int dir = 0;dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || v[ny][nx]) continue;
                if(min <= map[ny][nx] && max >= map[ny][nx]){
                    v[ny][nx] = true;
                    queue.add(new int[]{ny, nx});
                }
            }
        }
        return false;
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >=n || x < 0;
    }

}