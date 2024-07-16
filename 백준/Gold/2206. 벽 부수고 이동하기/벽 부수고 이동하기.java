import java.util.*;
import java.io.*;

public class Main {
    static int [][] map;
    static boolean [][][] v;//
    static StringTokenizer stk;
    static int N;
    static int M;
    static int res;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,1,0,-1};
    static void bfs(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[]{0, 0,1,0});
        v[0][0][0] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int k= 0;k<4;k++){
                int ny = now[0] + dy[k];
                int nx = now[1] + dx[k];
                if(ny>=N || ny<0 || nx>=M || nx<0) continue;
                if(ny == N-1 && nx == M-1){
                    int val = now[2]+1;
                    if(res > val) {
                        res = val;
                    }
                }
                if(now[3] != 0 && map[ny][nx] == 0 && !v[1][ny][nx]){
                    v[1][ny][nx] = true;
                    queue.add(new int[]{ny,nx,now[2]+1, now[3]});
                }
                if(now[3] == 0 && map[ny][nx] == 0 && !v[0][ny][nx]){
                    v[0][ny][nx] = true;
                    queue.add(new int[]{ny,nx,now[2]+1, now[3]});
                }
                if(now[3]==0  &&map[ny][nx] == 1 && !v[0][ny][nx]){
                    v[0][ny][nx] = true;
                    queue.add(new int[]{ny,nx,now[2]+1, now[3]+1});
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        // 상하 좌우 인접한곳 방문 그리고 최단거리 구하기 -> BFS
        // 근데 여기서 방문한곳을 방문하지 않는것도 중요하지만 내가 벽을 부쉇던 곳은 다음 경우에서 똑같은곳을 또 부수면 안된다.
        // 따라서 방문체크 배열이 한칸당 두가지 이상의 정보를 담아야 한다.
        // 시작좌표는 0,0
        // 탈출조건은 N-1,M-1을 만났을 경우이다.
        // 내가 벽을 부수고 해당칸에 도달한건지, 그런적 없이 도달한건지를 구분해야한다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new int[N][M];
        v = new boolean[2][N][M]; // 벽을 부수고 도착한건지 그냥 도착한건지 구분해야 한다.
        res = Integer.MAX_VALUE;
        for(int i = 0;i<N;i++){
            String t = bf.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = t.charAt(j)-'0';
            }
        }
        if(N == 1 && M == 1){
            res = 1;
        }else{
            bfs();
        }
        if(res == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(res);
        }
    }
}