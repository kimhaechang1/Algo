import java.util.*;
import java.io.*;

// start 19:25
// end
public class Main {
    static int h;
    static int w;
    static int [][] map;
    static StringTokenizer stk;
    static int [] hInfo;
    static int [][] board;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk= new StringTokenizer(bf.readLine());
        h = Integer.parseInt(stk.nextToken());
        w = Integer.parseInt(stk.nextToken());
        map = new int[h][w];
        hInfo = new int[w];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<w;i++){
            hInfo[i] = Integer.parseInt(stk.nextToken());
        }
        // 가장 왼쪽부터 탐색하면 될듯?
        // 왼쪽 아래칸부터 오른쪽으로 탐색하고 다시 왼쪽 첫줄부터 아래쪽으로 탐색하고
        // 우선 map에 벽을 나타내는 flag = 2, 물을 나타내는 flag 는 1, 빈공간은 0
        board = new int[h][w];
        for(int i= 0;i<w;i++){
            for(int j = h-1;j>=h-hInfo[i] ;j--){
                board[j][i] = 2;
            }
        }
        for(int i = h-1;i>-1;i--){
            for(int j = 0;j<w;j++){
                if(board[i][j] == 0){
                    if(check(i, j)){
                        boolean keep = false;
                        for(int k = j;k<w;k++){
                            if(board[i][k] == 2){
                                keep = true;
                                break;
                            }
                        }
                        if(keep){
                            for(int k = j;k<w;k++){
                                if(board[i][k] == 2){
                                    break;
                                }
                                board[i][k] = 1;
                            }
                        }
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0;i<h;i++){
            for(int j= 0;j<w;j++){
                if(board[i][j] == 1) cnt++;
            }
        }
        System.out.print(cnt);
    }
    static void print(int [][] board){
        for(int i= 0;i<board.length;i+=1){
            System.out.println(Arrays.toString(board[i]));
        }
    }
    static boolean check(int i, int j){
        int ny = i + dy[2];
        int nx = j + dx[2];
        if(OOB(ny,nx) || board[ny][nx] == 0) return false;
        ny = i + dy[1];
        nx = j + dx[1];
        if(OOB(ny, nx) || board[ny][nx] > 0) return true;
        return false;
    }
    static boolean OOB(int ny, int nx){
        return ny >= h || ny < 0 || nx >= w || nx < 0;
    }
}
