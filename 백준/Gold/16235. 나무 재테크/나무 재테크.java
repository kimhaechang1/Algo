import java.util.*;
import java.io.*;

// start 09:16
// end
public class Main {
    static int n;
    static int m;
    static int k;
    static int [][] added;
    static int [][] info;
    static int [][] board;
    static StringTokenizer stk;
    static int [] dy = {-1,1,0,0,-1,1,1,-1};
    static int [] dx = {0,0,-1,1,1,-1,1,-1};
    static PriorityQueue<Data> pq;
    static Queue<Data> died;
    static class Data implements Comparable<Data>{
        int y;
        int x;
        int age;
        boolean willDie;
        public Data(int y, int x , int age){
            this.y = y;
            this.x = x;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Data{" +
                "y=" + y +
                ", x=" + x +
                ", age=" + age +
                ", willDie=" + willDie +
                '}';
        }

        public Data(int y, int x , int age, boolean die){
            this.y = y;
            this.x = x;
            this.age = age;
            this.willDie = die;
        }


        public void setWillDie(boolean value){
            this.willDie = value;
        }

        public int compareTo(Data o){
            return this.age - o.age;
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        board = new int[n][n];
        added = new int[n][n];
        for(int i= 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j= 0;j<n;j++){
                added[i][j] = Integer.parseInt(stk.nextToken());
                board[i][j] = 5;
            }
        }
        info = new int[m][3];
        pq = new PriorityQueue<>();
        died = new ArrayDeque<>();
        for(int i = 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken())-1;
            info[i][1] = Integer.parseInt(stk.nextToken())-1;
            info[i][2] = Integer.parseInt(stk.nextToken());
            pq.add(new Data(info[i][0], info[i][1], info[i][2]));
        }
        // 시뮬레이션인듯
        // 각 칸마다 기본적으로 양분 5가 있음
        // 나무는 나이가 있고 하나의 칸에 여러 나무가 있을수 있다.
        // 1. 나무는 자신의 나이만큼 양분을 먹음, 칸에 여러나무가있을 경우 가장 나이가 어린 나무가 먼저 양분을 먹음
        // 1. 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 사망
        // 2. 죽은 나무는 나이를 2로 나눈값이 나무가 있던 칸에 양분으로 스며듬
        // 3. 인접한 8칸에 번식하여 나이가 1인 나무가 생성됨
        // 4. 각 땅에 양분을 추가
        while(k-- > 0){
            spring();
            summer();
            fall();
            winter();

        }
        System.out.println(check());
    }
    static void spring() {
        Queue<Data> temp = new ArrayDeque<>();
        while(!pq.isEmpty()){
            Data now = pq.poll();
            if(board[now.y][now.x] >= now.age){
                board[now.y][now.x] -= now.age;
                now.age = now.age + 1;
            }else{
                died.add(new Data(now.y, now.x, now.age));
                continue;
            }
            temp.add(new Data(now.y, now.x, now.age, now.willDie));
        }
        while(!temp.isEmpty()){
            pq.add(temp.poll());
        }
    }
    static void summer(){
        while(!died.isEmpty()){
            Data die = died.poll();
            board[die.y][die.x] += die.age / 2;
        }
    }
    static void fall(){
        Queue<Data> temp = new ArrayDeque<>();
        while(!pq.isEmpty()){
            Data now = pq.poll();
            if(now.age % 5 == 0){
                for(int dir = 0;dir<8;dir++){
                    int ny = now.y + dy[dir];
                    int nx = now.x + dx[dir];
                    if(OOB(ny, nx)) continue;
                    temp.add(new Data(ny, nx, 1));
                }
            }
            temp.add(new Data(now.y, now.x, now.age));

        }
        while(!temp.isEmpty()){
            pq.add(temp.poll());
        }
    }
    static void winter(){
        for(int i= 0;i<n;i++){
            for(int j = 0;j<n;j++){
                board[i][j] += added[i][j];
            }
        }

    }
    static int check(){
        return pq.size();
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >=n || x < 0;
    }
}
