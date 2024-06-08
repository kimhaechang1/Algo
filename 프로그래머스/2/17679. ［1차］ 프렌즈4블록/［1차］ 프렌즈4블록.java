import java.util.*;

class Solution {
    static char [][] map;
    static boolean [][] chk;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        for(int i = 0;i<m;i++){
            char [] ch = board[i].toCharArray();
            for(int j= 0;j<n;j++){
                map[i][j] = ch[j];
            }
        }
        while(true){
            chk = new boolean[m][n];
            if(!check()) break;
            answer += remove();
            down();
        }
        return answer;
    }
    static boolean check(){
        boolean flag = false;
        for(int i= 0;i<map.length;i++){
            for(int j = 0;j<map[i].length;j++){
                if(map[i][j] != '0'){
                    char cur = map[i][j];
                    // 위 왼 왼위 대각
                    dy = new int[]{-1,0,-1};
                    dx = new int[]{0,-1,-1};
                    boolean isOk = true;
                    for(int dir = 0;dir<3;dir++){
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];
                        if(OOB(ny, nx) || map[ny][nx]!= cur){
                            isOk = false;
                            break;
                        }
                    }
                    if(isOk){
                        flag = true;
                        for(int dir = 0;dir<3;dir++){
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            chk[ny][nx] = true;
                        }    
                    }
                    isOk = true;
                    // 위 오른 오른위 대각
                    dy = new int[]{-1,0,-1};
                    dx = new int[]{0,1,1};
                    for(int dir = 0;dir<3;dir++){
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];
                        if(OOB(ny, nx) || map[ny][nx]!= cur){
                            isOk = false;
                            break;
                        }
                    }
                    if(isOk){
                        flag = true;
                        for(int dir = 0;dir<3;dir++){
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            chk[ny][nx] = true;
                        }    
                    }
                    isOk = true;
                    // 아래 왼 왼아래 대각
                    dy = new int[]{1,0,1};
                    dx = new int[]{0,-1,-1};
                    for(int dir = 0;dir<3;dir++){
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];
                        if(OOB(ny, nx) || map[ny][nx]!= cur){
                            isOk = false;
                            break;
                        }
                    }
                    if(isOk){
                        flag = true;
                        for(int dir = 0;dir<3;dir++){
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            chk[ny][nx] = true;
                        }    
                    }
                    isOk = true;
                    // 아래 오른 오른아래 대각
                    dy = new int[]{1, 0, 1};
                    dx = new int[]{0, 1, 1};
                    for(int dir = 0;dir<3;dir++){
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];
                        if(OOB(ny, nx) || map[ny][nx]!= cur){
                            isOk = false;
                            break;
                        }
                    }
                    if(isOk){
                        flag = true;
                        for(int dir = 0;dir<3;dir++){
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            chk[ny][nx] = true;
                        }    
                    }
                }
            }
        }
        return flag;
    }
    static int remove(){
        int cnt = 0;
        for(int i= 0;i<map.length;i++){
            for(int j = 0;j<map[i].length;j++){
                if(chk[i][j]){
                    map[i][j] = '0';
                    cnt++;
                }
            }
        }
        return cnt;
    }
    static void down(){
        // 중력 작용
        for(int i = 0;i<map[0].length;i++){
            char [] cp = new char[map.length];
            int pointer = map.length-1;
            for(int j = map.length-1;j>-1;j--){
                if(map[j][i] == '0') continue;
                cp[pointer--] = map[j][i];
            }
            for(int j= map.length-1;j>-1;j--){
                if(cp[j] - 'A' >=0 && cp[j] - 'A' <= 26){
                    map[j][i] = cp[j];
                }else{
                    map[j][i] = '0';
                }
            }
        }
    }
    static boolean OOB(int y, int x){
        return y>= map.length || y < 0 || x >= map[0].length || x < 0;
    }
    
}