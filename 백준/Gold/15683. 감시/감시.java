import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int M;
    // 각 카메라별로 뽑을수 있는 모든 각도를 뽑고나서 시뮬레이션을 돌리면 된다.
    static int [][] map;
    static StringTokenizer stk;
    static int [][] temp;
    static int [] cam;
    static int min;
    static int [] dy = {-1,1,0,0};
    // 상하좌우
    static int [] dx = {0,0,-1,1};
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new int[N][M];
        list = new ArrayList<>();
        int c = 0;
        for(int i=0;i<N;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j= 0;j<M;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6){
                    c++;
                    list.add(new int[]{i,j,map[i][j]});
                }
            }
        }

        temp = new int[N][M];
        cam = new int[c];
        //
//        for(int k= 0;k<N;k++){
//            for(int m= 0;m<M;m++){
//                System.out.print(map[k][m]+" ");
//            }
//            System.out.println();
//        }
        min = Integer.MAX_VALUE;
        dfs(0 );
        System.out.println(min);
    }
    static void dfs(int depth){
        if(depth == cam.length){
            copyMap();
            for(int i= 0;i<cam.length;i++){
                int d = cam[i];
                int sy = list.get(i)[0];
                int sx = list.get(i)[1];
                int type = list.get(i)[2];
                // 상 하 좌 우
                switch(type){
                    case 1:
                        dfsTemp(sy,sx, dy[d],dx[d]);
                        break;
                    case 2:
                        if(d == 0 || d==1){
                            dfsTemp(sy,sx,dy[0],dx[0]);
                            dfsTemp(sy,sx, dy[1],dx[1]);
                        }else{
                            dfsTemp(sy,sx,dy[2],dx[2]);
                            dfsTemp(sy,sx, dy[3],dx[3]);
                        }
                        break;
                    case 3:
                        if(d == 0){// 상 우
                            dfsTemp(sy, sx, dy[0],dx[0]);
                            dfsTemp(sy, sx, dy[3],dx[3]);
                        }else if(d == 1){
                            dfsTemp(sy, sx, dy[3],dx[3]);
                            dfsTemp(sy, sx, dy[1],dx[1]);
                            // 우 하
                        }else if(d == 2){
                            dfsTemp(sy, sx, dy[2],dx[2]);
                            dfsTemp(sy, sx, dy[1],dx[1]);
                            // 좌 하
                        }else if(d == 3){
                            dfsTemp(sy, sx, dy[0],dx[0]);
                            dfsTemp(sy, sx, dy[2],dx[2]);
                            // 상 좌
                        }
                        break;
                    case 4:
                        if(d == 0){
                            // 상 좌 우
                            dfsTemp(sy, sx, dy[0],dx[0]);
                            dfsTemp(sy, sx ,dy[2],dx[2]);
                            dfsTemp(sy, sx, dy[3],dx[3]);
                        }else if(d == 1){
                            // 상 우 하
                            dfsTemp(sy, sx, dy[0],dx[0]);
                            dfsTemp(sy, sx, dy[3],dx[3]);
                            dfsTemp(sy, sx, dy[1],dx[1]);

                        }else if(d == 2){
                            // 좌 우 하
                            dfsTemp(sy, sx, dy[3],dx[3]);
                            dfsTemp(sy, sx, dy[2],dx[2]);
                            dfsTemp(sy, sx, dy[1],dx[1]);

                        }else if(d == 3){
                            // 좌 상 하
                            dfsTemp(sy, sx, dy[0],dx[0]);
                            dfsTemp(sy, sx, dy[1], dx[1]);
                            dfsTemp(sy, sx, dy[2],dx[2]);
                        }
                        break;
                    case 5:
                        // 상하좌우
                        dfsTemp(sy, sx, dy[0],dx[0]);
                        dfsTemp(sy, sx, dy[1],dx[1]);
                        dfsTemp(sy, sx ,dy[2],dx[2]);
                        dfsTemp(sy, sx, dy[3],dx[3]);
                        break;
                }
            }
            int c= 0;
            for(int i= 0;i<N;i++){
                for(int j= 0;j<M;j++){
//                    System.out.print(temp[i][j]+" ");
                    if(temp[i][j] == 0) c++;
                }
            }
            if(min > c){
                min = c;
            }
            return;
        }

        for(int i = 0;i<4;i++){
            cam[depth] = i;
            dfs(depth+1);
        }
    }

    public static void copyMap(){
        for(int i= 0;i<N;i++){
            for(int j=0;j<M;j++){
                temp[i][j] = map[i][j];
            }
        }
    }

    static void dfsTemp(int sy, int sx, int dy, int dx){
        int ny = sy+dy;
        int nx = sx+dx;
        if(ny >= N || ny < 0 || nx >=M || nx<0 || temp[ny][nx]== 6) return;
        if(temp[sy+dy][sx+dx]>0 && temp[sy+dy][sx+dx] != 6) dfsTemp(ny, nx, dy, dx);
        temp[ny][nx] = 9;
        dfsTemp(ny,nx, dy, dx);
    }
}