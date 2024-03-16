import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static int[][] map;
    static boolean[][] visit;
    static int[] dh = { -1, 1, 0, 0 , -1, -1 , 1 ,1};
    static int[] dw = { 0, 0, -1, 1 , 1, -1, -1, 1};
    static StringBuilder sb = new StringBuilder();
    static int W;
    static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            if (W == 0 && H == 0) break;
            
            map = new int[H][W];
            visit = new boolean[H][W];
            
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int cnt=0;
            
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {// 방문X, 땅인 곳
                        dfs(i, j);
                        cnt++;
                    }
                    
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);

    }

    static void dfs(int h, int w) {
        visit[h][w] = true;
        
        for (int i = 0; i < 8; i++) {
            int th = h + dh[i];
            int tw = w + dw[i];
            
            
            if (th < 0 || th >= H || tw < 0 || tw >= W) {
                continue;
            }	
            if (map[th][tw] == 1 && !visit[th][tw]) {
                visit[th][tw] = true;
                dfs(th, tw);
            }
        }

    }

}