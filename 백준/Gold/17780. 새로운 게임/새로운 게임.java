import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int k;
    static int [][] map;
    static int [][] info;

    static int [] dy = {0,0,-1,1};
    static int [] dx = {1,-1,0,0};
    static StringTokenizer stk;
    static boolean flag = false;
    static class Data{
        int idx;
        int dir;
        public Data(int idx, int dir){
            this.idx = idx;
            this.dir = dir;
        }
        public String toString(){
            return "[ idx: "+idx+" dir: "+dir+" ]";
        }
    }
    static int time = 1000;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        for(int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0;j<n;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        info = new int[k][3];
        for(int i= 0;i<k;i++){
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            info[i][2] = Integer.parseInt(stk.nextToken());
        }
        // 하나의 말 위에 다른 말을 올릴 수 있다. -> 겹치기 가능
        // 흰 빨 파
        // K개의 말을 놓고 시작함, 말번호 1~ K 이동방향도 정해져 있음
        // 말이 겹쳐있을 경우 가장 아래에있는 말만 이동할 수 있고, 해당 말이 이동하면 같이 이동함.
        // 턴 지속중, 말이 4개이상 쌓이는 순간 게임이 종료됨
        // 빨간색칸의 경우 이동한 후에 순서는 뒤집는다.
        // 파란색의 경우 이동방향을 반대로 하고 한칸 이동, 이동하려는 칸이 파란색의 경우에는 이동하지 않고 방향만 반대로
        // 벗어나는경우 반대로 방향을 바꿈
        ArrayDeque<Data>[][] maps = new ArrayDeque[n][n];
        init(maps);
        while(!flag && time-- > 0){
            HashMap<Integer, int []> cache = new HashMap<>();
            go(cache, maps);
            if(flag) break;
            scan(maps);
        }
        if(flag){
            System.out.println(1000-time);
        }else{
            System.out.println(-1);
        }
    }
    static void init(ArrayDeque<Data>[][] map){
        for(int i = 0;i<n;i++){
            for(int j= 0;j<n;j++){
                map[i][j] = new ArrayDeque<>();
            }
        }
        for(int i= 0;i<k;i++){
            int y = info[i][0]-1;
            int x = info[i][1]-1;
            int dir = info[i][2]-1;
            map[y][x].addLast(new Data(i, dir));
        }
    }
    static void go(HashMap<Integer, int[]> cache, ArrayDeque<Data>[][] maps){
        for(int i = 0;i<k;i++){
            label: for(int j = 0;j<n;j++){
                for(int p =0;p<n;p++){
                    Data front = maps[j][p].peekFirst();
                    if(front!= null && front.idx == i){
                        int ny = j + dy[front.dir];
                        int nx = p + dx[front.dir];
                        int ndir = front.dir;
                        if(OOB(ny, nx)){
                            blue(j, p, maps);
                        }else if(map[ny][nx] == 0){
                            white(j, p, ny, nx, maps);
                        }else if(map[ny][nx] == 1){
                            red(j, p, ny, nx, maps);
                        }else{
                            blue(j, p, maps);
                        }
                        break label;
                    }

                }
            }
        }
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= n || x < 0;
    }
    static void OOBProcessor(Data target, int dir){
        if((dir + 1) % 2 == 0){
            dir--;
        }else{
            dir++;
        }
        target.dir = dir;
    }
    static void white(int py, int px, int ny, int nx, ArrayDeque<Data>[][] maps){
        while(!maps[py][px].isEmpty()){
            maps[ny][nx].addLast(maps[py][px].pollFirst());
        }
        if(maps[ny][nx].size() >= 4){
            flag = true;
        }
    }
    static void red(int py, int px, int ny, int nx, ArrayDeque<Data>[][] maps){
        while(!maps[py][px].isEmpty()){
            maps[ny][nx].addLast(maps[py][px].pollLast());
        }
        if(maps[ny][nx].size() >= 4){
            flag = true;
        }
    }
    static void blue(int py, int px, ArrayDeque<Data>[][] maps){
        Data peek = maps[py][px].peekFirst();
        int dir = peek.dir;
        int rdir = dir;
        if((dir + 1) % 2 == 0){
            rdir = dir-1;
        }else{
            rdir = dir+1;
        }
        int y = py + dy[rdir];
        int x = px + dx[rdir];
        if(OOB(y, x) || map[y][x] == 2){
            OOBProcessor(peek, dir);
        }else if(map[y][x] == 0){
            peek.dir = rdir;
            white(py, px, y, x, maps);
        }else{
            peek.dir = rdir;
            red(py, px, y, x, maps);
        }
    }
    static void scan(ArrayDeque<Data>[][] maps){
        for(int i = 0;i<n;i++){
            for(int j= 0;j<n;j++){
                if(maps[i][j].size() >= 4){
                    flag = true;
                    return;
                }
            }
        }
    }
    static void print(ArrayDeque<Data>[][] maps){
        for(int i = 0;i<n;i++){
            for(int j= 0;j<n;j++){
                Queue<Data> queue = new ArrayDeque<>();
                if(maps[i][j].size() > 0){
                    System.out.print("i: "+i +" j: "+j+ "size: "+maps[i][j].size());
                }
                while(!maps[i][j].isEmpty()){
                    System.out.print(maps[i][j].peekFirst()+" -> ");
                    queue.add(maps[i][j].pollFirst());
                }
                while(!queue.isEmpty()){
                    maps[i][j].addLast(queue.poll());
                }
            }
            System.out.println();
        }
    }
}
