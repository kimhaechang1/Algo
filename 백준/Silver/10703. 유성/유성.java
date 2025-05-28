import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int r, s;
    static char[][] map;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 각각의 컬럼에서 '#' 과 닿기까지의 최소거리를 구하고, 그 최소거리만큼만 중력을 작용시킨다.
        int min = 3001;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < s; j++) {
                if (map[i][j] != 'X') continue;
                // System.out.println("y: "+i+" x: "+ j+" height: "+ getHeight(i, j));
                min = Math.min(min, getHeight(i, j));
            }
        }
        // System.out.println("이만큼 중력 필요해요: "+min);
        for(int i = r - 1; i > -1; i--) {
            for(int j = 0; j < s; j++) {
                if (map[i][j] != 'X') continue;
                move(i, j, min);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < s; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    void move(int y, int x, int min) {

        map[y][x] = '.';
        map[y + min][x] = 'X';
    }

    int getHeight(int y, int x) {
        int height = 0;
        while(y < r && map[y][x] == 'X') y++;
        while(y < r) {
            if (map[y][x] == 'X') return 3001;
            if (map[y][x] == '#') break;
            height++;
            y++;
        }
        return height;
    }

    void input() throws Exception {

        stk = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(stk.nextToken());
        s = Integer.parseInt(stk.nextToken());
        map = new char[r][s];
        for(int i = 0; i < r; i++) {
            map[i] = bf.readLine().toCharArray();
        }
    }
}