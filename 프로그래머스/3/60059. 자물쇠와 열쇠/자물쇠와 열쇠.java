import java.util.*;

class Solution {
    static int[][][] keys;
    static int n;
    static int m;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        // 홈과 돌기가 있음
        // 모든 한쪽의 홈은 반대쪽의 돌기여야함 즉, key에서 1인 인덱스는 lock에서 0이어야함 -> 반대도 마찬가지
        // 단, key에 대해서 lock에 빗대었을 때, 맵밖으로 나간 key 칸부분은 열수 있는지 없는지 여부에서 제외한다.
        // key -> M, lock -> N
        n = lock.length;
        m = key.length;
        keys = new int[4][m][m];
        rotate(key);
        //keyPrint();
        for(int dir = 0;dir<4;dir++) {
            if (check(lock, dir)) {
                return true;
            }
        }
        return false;
    }
    static void rotate(int[][] map) {
        for(int i= 0;i<m;i++) {
            keys[0][i] = map[i].clone();
        }
        for(int dir = 1;dir<4;dir++) {
            int prev = dir-1;
            for(int i = 0;i<m;i++) {
                for(int j = 0;j<m;j++) {
                    keys[dir][j][m-1-i] = keys[prev][i][j];
                }
            }
        }
    }
    static void keyPrint() {
        for(int dir = 0;dir<4;dir++) {
            System.out.println("dir: "+dir);
            for(int i = 0;i<m;i++) {
                for(int j =0;j<m;j++) {
                    System.out.print(keys[dir][i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("#######################");
        }
    }
    static boolean check(int[][] l, int dir) {
        // lock 기준으로 왼쪽위 key  기준으로 오른쪽 아래로 맞대어 검사
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if (go(i, j, keys[dir], l)) {
                    return true;   
                }
            }
        }
        // lock기준으로 오른쪽 아래 key 기준으로 왼쪽위 맞대어 검사
        for(int i = n-1;i>-1;i--) {
            for(int j = n-1;j>-1;j--) {
                if(go1(i, j, keys[dir], l)) {
                    return true;
                }
            }
        }
        // lock 기준으로 오른쪽 위 key 기준으로 왼쪽 아래로 맞대어 검사
        for(int i = 0;i<n;i++) {
            for(int j = n-1;j> -1;j--) {
                if (go2(i, j, keys[dir], l)) {
                    return true;   
                }
            }
        }
        // lock기준으로 왼쪽아래 아래 key 기준으로 오른쪽 위 맞대어 검사
        for(int i = n-1;i>-1;i--) {
            for(int j = 0;j < n;j++) {
                if(go3(i, j, keys[dir], l)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    static boolean go(int sly, int slx, int[][] key, int[][] lock) {
        int[][] temp = new int[n][n];
        for(int i = 0;i<n;i++) {
            temp[i] = lock[i].clone();
        }
        int ly = sly;
        int lx = slx;
        int ky = m-1;
        int kx = m-1;
        int xOffSet = Math.min(m, slx + 1);
        int yOffSet = Math.min(m, sly + 1);
        for(int i = 0;i<yOffSet;i++) {
            for(int j = 0;j<xOffSet;j++) {
                if(temp[ly-i][lx-j] == key[ky-i][kx-j]) {
                    return false;
                } else {
                    if (temp[ly-i][lx-j] == 0 && key[ky-i][kx-j] == 1) {
                        temp[ly-i][lx-j] = 1;
                    }
                }
            }
        }
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(temp[i][j] == 0) return false;
            }
        }
        return true;
    }
    
    static boolean go1(int sly, int slx, int[][] key, int[][] lock) {
        int[][] temp = new int[n][n];
        for(int i = 0;i<n;i++) {
            temp[i] = lock[i].clone();
        }
        int ly = sly;
        int lx = slx;
        int ky = 0;
        int kx = 0;
        int xOffSet = Math.min(m, n - slx);
        int yOffSet = Math.min(m, n - sly);
        for(int i = 0;i<yOffSet;i++) {
            for(int j = 0;j<xOffSet;j++) {
                if(temp[ly+i][lx+j] == key[ky+i][kx+j]) {
                    return false;
                } else {
                    if (temp[ly+i][lx+j] == 0 && key[ky+i][kx+j] == 1) {
                        temp[ly+i][lx+j] = 1;
                    }
                }
            }
        }
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(temp[i][j] == 0) return false;
            }
        }
        return true;
    }
    static boolean go2(int sly, int slx, int[][] key, int[][] lock) {
        int[][] temp = new int[n][n];
        for(int i = 0;i<n;i++) {
            temp[i] = lock[i].clone();
        }
        int ly = sly;
        int lx = slx;
        int ky = m-1;
        int kx = 0;
        int xOffSet = Math.min(m, n - slx);
        int yOffSet = Math.min(m, sly + 1);
        for(int i = 0;i<yOffSet;i++) {
            for(int j = 0;j<xOffSet;j++) {
                if(temp[ly-i][lx+j] == key[ky-i][kx+j]) {
                    return false;
                } else {
                    if (temp[ly-i][lx+j] == 0 && key[ky-i][kx+j] == 1) {
                        temp[ly-i][lx+j] = 1;
                    }
                }
            }
        }
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(temp[i][j] == 0) return false;
            }
        }
        return true;
    }
    static boolean go3(int sly, int slx, int[][] key, int[][] lock) {
        int[][] temp = new int[n][n];
        for(int i = 0;i<n;i++) {
            temp[i] = lock[i].clone();
        }
        int ly = sly;
        int lx = slx;
        int ky = 0;
        int kx = m-1;
        int xOffSet = Math.min(m, slx+1);
        int yOffSet = Math.min(m, n - sly);
        for(int i = 0;i<yOffSet;i++) {
            for(int j = 0;j<xOffSet;j++) {
                if(temp[ly+i][lx-j] == key[ky+i][kx-j]) {
                    return false;
                } else {
                    if (temp[ly+i][lx-j] == 0 && key[ky+i][kx-j] == 1) {
                        temp[ly+i][lx-j] = 1;
                    }
                }
            }
        }
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(temp[i][j] == 0) return false;
            }
        }
        return true;
    }
    
}