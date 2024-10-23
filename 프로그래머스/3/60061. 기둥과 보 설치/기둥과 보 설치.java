import java.util.*;

class Solution {
    static int[][] bFrames;
    static int[][] gFrames;
    static ArrayList<int[]> structList;
    static int N;
    public int[][] solution(int n, int[][] build_frame) {

        N = n+1;
        bFrames = new int[N][N];
        gFrames = new int[N][N];
        for(int i = 0;i<N;i++) {
            Arrays.fill(bFrames[i], -1);
            Arrays.fill(gFrames[i], -1);
        }
        structList = new ArrayList<>();
        for(int[] build: build_frame) {
            if (build[3] == 0) {
                if (removeChecker(build[0], build[1], build[2])) {
                    remove(build[0], build[1], build[2]);
                }
            } else {
                if (installChecker(build[0], build[1], build[2])) {
                    install(build[0], build[1], build[2]);
                }
            }
        }

        TreeSet<Integer> set = new TreeSet<>((a, b) -> {
            int[] aSt = structList.get(a);
            int[] bSt = structList.get(b);
            if (aSt[0] == bSt[0]) {
                if (aSt[1] == bSt[1]) {
                    return aSt[2] - bSt[2];
                }
                return aSt[1] - bSt[1];
            }
            return aSt[0] - bSt[0];
        });
        for(int i = 0;i<N;i++) {
            for(int j =0;j<N;j++) {
                if (bFrames[i][j] > -1) {
                    set.add(bFrames[i][j]);
                }
                if (gFrames[i][j] > -1) {
                    set.add(gFrames[i][j]);
                }
            }
        }
        int[][] answer = new int[set.size()][3];
        for(int i = 0;i<answer.length;i++) {
            int[] struct = structList.get(set.pollFirst());
            answer[i][0] = struct[0];
            answer[i][1] = struct[1];
            answer[i][2] = struct[2];
        }
        return answer;
    }
    static boolean installChecker(int x, int y, int type) {
        if (type == 0) {
            if (y == 0) return true;
            if (bFrames[x][y] != -1 && structList.get(bFrames[x][y])[2] == 1) return true;
            if (!OOB(x, y-1) && gFrames[x][y-1] > -1 && structList.get(gFrames[x][y-1])[2] == 0) return true;
            if (!OOB(x-1, y) && bFrames[x-1][y] > -1 && structList.get(bFrames[x-1][y])[2] == 1) return true;
        } else {
            if (!OOB(x, y-1) && gFrames[x][y-1] > -1 && structList.get(gFrames[x][y-1])[2] == 0) return true;
            if (!OOB(x+1, y-1) && gFrames[x+1][y-1] > -1 && structList.get(gFrames[x+1][y-1])[2] == 0) return true;
            if (!OOB(x-1,y) && bFrames[x-1][y] > -1 && structList.get(bFrames[x-1][y])[2] == 1) {
                if (!OOB(x+1,y) && bFrames[x+1][y] > -1 && structList.get(bFrames[x+1][y])[2] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    static void install(int x, int y, int type) {
        int[] struct = new int[]{x, y, type};
        structList.add(struct);
        if (type == 0) {
            gFrames[x][y] = structList.size()-1;
        } else {
            bFrames[x][y] = structList.size()-1;
        }
    }
    static boolean removeChecker(int x, int y, int type) {
        int stIdx;
        if (type == 0) {
            stIdx = gFrames[x][y];
            gFrames[x][y] = -1;
        } else {
            stIdx = bFrames[x][y];
            bFrames[x][y] = -1;
        }

        boolean can = true;
        outer: for(int i = 0;i<N;i++) {
            for(int j = 0;j<N;j++) {
                int fIdx;
                if (bFrames[i][j] > -1) {
                    fIdx = bFrames[i][j];
                    int[] st = structList.get(fIdx);
                    if (!installChecker(st[0], st[1], st[2])) {
                        can = false;
                        break outer;
                    }
                }
                if (gFrames[i][j] > -1) {
                    fIdx = gFrames[i][j];
                    int[] st = structList.get(fIdx);
                    if (!installChecker(st[0], st[1], st[2])) {
                        can = false;
                        break outer;
                    }
                }
            }
        }
        if (type == 0) {
            gFrames[x][y] = stIdx;
        } else {
            bFrames[x][y] = stIdx;
        }
        return can;
    }
    static void remove(int x,int y, int type) {
        if (type == 0) {
            gFrames[x][y] = -1;
        } else {
            bFrames[x][y] = -1;
        }
    }

    static boolean OOB(int x, int y) {
        return x >= N || x < 0 || y >= N || y < 0;
    }

}