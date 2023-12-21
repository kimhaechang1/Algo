import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;

    static int C;
    static class Map{
        int time;
        int size;
        int sharkid;

        public Map(int time, int size, int id){
            this.size = size;
            this.time = time;
            this.sharkid = id;
        }

        @Override
        public String toString() {
            return "Map{" +
                    "time=" + time +
                    ", size=" + size +
                    ", sharkid=" + sharkid +
                    '}';
        }
    }
    static Map [][] map;
    static class Shark{
        int y;
        int x;
        int s; // 속력
        int d; // 방향 1 상, 2 하, 3 우, 4 좌
        int z; // 크기
        int time; // 시간
        boolean isDead; // 죽었는가 잡혔는가 등


        @Override
        public String toString() {
            return "Shark{" +
                    "y=" + y +
                    ", x=" + x +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    ", time=" + time +
                    ", isDead=" + isDead +
                    '}';
        }

        public Shark(int y, int x, int s , int d, int z, int time, boolean isDead) {
            this.y = y;
            this.x = x;
            this.s = s;
            this.d = d;
            this.z = z;
            this.time = time;
            this.isDead = isDead;
        }

    }
    static Shark [] sharks;
    static StringTokenizer stk;
    static int [] dy = {0,-1,1,0,0};
    static int [] dx = {0,0,0,1,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        map = new Map[N+1][M+1];
        sharks = new Shark[C];
        for(int i= 0;i<C;i++){
            stk = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            sharks[i] = new Shark(y,x,s,d,z,0,false);
            map[y][x] = new Map(0, z, i);
        }
        int sx = 0; // 낚시왕 위치
        int sum =0; // 최종 잡은 상어들 무게의 합
        while(true){
            // 주인장 이동시키기

            sx++;
            if(sx == M+1) break;
            int minY = Integer.MAX_VALUE;
            int sharkid = -1;
            for(int i= 0;i<C;i++){
                if(!sharks[i].isDead && sharks[i].x == sx){
                    if(minY > sharks[i].y){
                        minY = sharks[i].y;
                        sharkid = i;
                    }
                }
            }
            // 상어잡기
            if(sharkid != -1){
                sum+=sharks[sharkid].z;
                sharks[sharkid].isDead = true;
//                System.out.println("잡힌 물고기 : "+sharks[sharkid]);
            }

            // 상어이동시키기
            for(int i= 0;i<C;i++){
                if(!sharks[i].isDead){
                    int go = -1;
                    if(sharks[i].d == 1 || sharks[i].d == 2){
                        go = (sharks[i].s%((N-1)*2));
                    }else if(sharks[i].d == 3 || sharks[i].d == 4){
                        go  = (sharks[i].s%((M-1)*2));
                    }
                    for(int j= 0;j<go;j++){
                        int ny = sharks[i].y + dy[sharks[i].d];
                        int nx = sharks[i].x + dx[sharks[i].d];
                        if(ny > N || ny < 1 || nx > M || nx < 1){
                            if(sharks[i].d == 1){
                                ny +=2;
                                sharks[i].d = 2;
                            }else if(sharks[i].d == 2){
                                ny-=2;
                                sharks[i].d = 1;
                            }else if(sharks[i].d == 3){
                                nx-=2;
                                sharks[i].d = 4;
                            }else{
                                nx+=2;
                                sharks[i].d = 3;
                            }
                        }
                        sharks[i].y = ny;
                        sharks[i].x = nx;
                    }
                    sharks[i].time=sharks[i].time+1;

                    if(map[sharks[i].y][sharks[i].x] == null){
                        map[sharks[i].y][sharks[i].x] = new Map(sharks[i].time,sharks[i].z,i);
                        //System.out.println(sharks[i]);
                    }else if(map[sharks[i].y][sharks[i].x].time < sharks[i].time){
                        map[sharks[i].y][sharks[i].x] = new Map(sharks[i].time,sharks[i].z,i);
                        //System.out.println(map[sharks[i].y][sharks[i].x]);
                    }
                    else if(map[sharks[i].y][sharks[i].x].time == sharks[i].time && map[sharks[i].y][sharks[i].x].size < sharks[i].z){
                        sharks[map[sharks[i].y][sharks[i].x].sharkid].isDead = true;
                        map[sharks[i].y][sharks[i].x].sharkid = i;
                        map[sharks[i].y][sharks[i].x].size =  sharks[i].z;
                        map[sharks[i].y][sharks[i].x].time =  sharks[i].time;
                    }else if(map[sharks[i].y][sharks[i].x].time == sharks[i].time && map[sharks[i].y][sharks[i].x].size > sharks[i].z){

                        sharks[i].isDead = true;
                    }
//                    System.out.println(sharks[map[3][3].sharkid]);
//                    if(sharks[i].y == 3 && sharks[i].x == 3){
//                        System.out.println(sharks[i]);
//                    }
                }

            }
//            System.out.println(map[2][4]);
//            for(int i = 0;i<=N;i++){
//                for(int j= 0;j<=M;j++){
//                    if(map[i][j] == null )continue;
//                    System.out.println("i j : "+i+" "+j+" "+map[i][j]);
//                }
//            }
//            System.out.println("----------------");
        }
        System.out.println(sum);

    }
}
