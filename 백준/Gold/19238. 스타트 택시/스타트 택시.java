import java.util.*;
import java.io.*;

public class Main{
    static int m;
    static int n;
    static int f;
    static int [][] map;
    static StringTokenizer stk;
    static ArrayList<int []> list;
    static int [][] start;
    static int [][] end;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,-1,0,1};
    static int sy,sx;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        f = Integer.parseInt(stk.nextToken());
        map = new int[n][n];
        start  = new int[n][n];
        end = new int[n][n];
        list = new ArrayList<>();
        for(int i= 0;i<n;i++){
            stk =new StringTokenizer(bf.readLine());
            for(int j = 0;j<n;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        stk = new StringTokenizer(bf.readLine());
        sy = Integer.parseInt(stk.nextToken())-1;
        sx = Integer.parseInt(stk.nextToken())-1;
        for(int i = 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            int sy = Integer.parseInt(stk.nextToken())-1;
            int sx = Integer.parseInt(stk.nextToken())-1;
            int ty = Integer.parseInt(stk.nextToken())-1;
            int tx = Integer.parseInt(stk.nextToken())-1;
            start[sy][sx] = i+1;

            list.add(new int[]{sy, sx, ty, tx});
        }
        // 다음 손님을 태우려 하다가 연료가 바닥나면 -1, 연모든 손님을 이동시킬 수 없는경우에도 -1
        // 남은 연료를 보여주면 됨
        // 1은 벽임
        // 항상 최단경로로만 이동하고, 승객의 위치와 도착지점이란게 있음
        // 현재 위치에서부터 최단거리가 가장 짧은 승객을 고른다. 그런 승객이 여러명인 경우 가장 위면서 왼쪽을 고른다.
        // 택시와 승객이 같은 위치에 있으면 최단거리는 0, 연료는 한 칸 이동할때 마다 1 소모
        // 해당 택시가 소모한 연료 양의 2배가 충전됨
        boolean [] isTerm = new boolean[m];
        boolean isCan = true;
        int cnt = 0;
        while(cnt < m){
            int [] info = select(); // start 맵에서 sy,sx로부터 가장 가까운 위치를 찾기
            // info[0] : 위치번호, -1이면 갈곳없음, info[1] 거기까지 가는데 걸리는 연료양
            // 해당 위치까지 사용되는 연료양도 바로 알 수 있다,
            // 벽으로
            // 둘러 쌓여서 못가는 경우도 있다.
            if(info[1] > f || info[0] == -1){
                isCan = false;
                break;
            }

            int [] yx = list.get(info[0]-1);

            sy = yx[0];
            sx = yx[1];
            f -= info[1];
            int [] tinfo = goTarget(yx[2], yx[3]);
            // tinfo[0] : 0 이면 성공, 1이면 연료에 상관없이 갈 수 없음
            // tinfo[1] : 가는데 필요한 연료 양
            if(tinfo[1] > f || tinfo[0] == 1){
                isCan = false;
                break;
            }
            sy = yx[2];
            sx = yx[3];
            f -= tinfo[1];
            f += (tinfo[1] * 2);
            cnt++;
            start[yx[0]][yx[1]] = 0;
            end[yx[2]][yx[3]] = 0;
        }
        if(isCan){
            System.out.print(f);
        }else{
            System.out.print(-1);
        }
    }
    static int [] select(){
        // todo : start맵을 sy, sx 좌표에서 부터 bfs를 돌면서 가장 가까운 시작지점을 찾는다.
        // return [0] : map[][], 만약 갈 곳이 없다면 -1, [1] : 연료양
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{sy, sx, 0});
        boolean [][] v = new boolean[n][n];
        int [] res = new int[2];
        res[0] = -1;
        v[sy][sx] = true;
        int y = n;
        int x = n;
        int dis = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(start[now[0]][now[1]] > 0){
                if(dis > now[2]){
                    dis = now[2];
                    res[1] = dis;
                    y = now[0];
                    x = now[1];
                    res[0] = start[now[0]][now[1]];
                }else if(dis ==now[2]){
                    if(y > now[0]){
                        y = now[0];
                        x = now[1];
                        res[0] = start[now[0]][now[1]];
                    }else if(y == now[0]){
                        if(x > now[1]){
                            x = now[1];
                            res[0] = start[now[0]][now[1]];
                        }
                    }
                }
                continue;
            }
            for(int dir = 0;dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] == 1) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, now[2]+1});
            }
        }
        return res;
    }
    static int [] goTarget(int ty, int tx){
        // todo : end맵을 sy, sx 좌표에서 부터 bfs를 돌면서 목적지까지의 사용한 연료양을 찾는다.
        // return [0] : 벽같은거에 막히지 않고 갈 수는 있음 = 0이면 갈 수 있음, 1이면 막힘, [1] : 그기까지 가는데 필요한 연료양
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{sy, sx, 0});
        boolean [][] v = new boolean[n][n];
        int [] res = new int[2];

        res[0] = 1;
        v[sy][sx] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == ty && now[1] == tx){
                res[0] = 0;
                res[1] = now[2];
                break;
            }
            for(int dir = 0;dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] == 1) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, now[2]+1});
            }
        }
        return res;
    }
    static boolean OOB(int ny, int nx){
        return ny >= n || ny < 0 || nx >= n || nx < 0;
    }
}