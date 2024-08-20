import java.util.*;
import java.io.*;

class Solution {
    static int offset = -2;
    static ArrayList<Integer> sizeList;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    public int solution(int[][] land) {
        // 전처리 해놓으면 풀수있을라나
        init(land);
        // System.out.println(sizeList);
        /*for(int i= 0;i<land.length;i++){
            System.out.println(Arrays.toString(land[i]));
        }*/
        int answer = 0;
        for(int i = 0;i<land[0].length;i++) {
            int cnt = 0;
            boolean[] used = new boolean[sizeList.size()];
            for(int j = 0;j<land.length;j++) {
                if (land[j][i] > 0 && !used[land[j][i] + offset]) {
                    used[land[j][i] + offset] = true;
                    cnt += sizeList.get(land[j][i] + offset);
                }
            }
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
    static void init(int[][] map) {
        int idx= 2;
        sizeList = new ArrayList<>();
        for(int i = 0;i<map.length;i++){
            for(int j = 0;j<map[0].length;j++) {
                if(map[i][j] == 1) {
                    sizeList.add(bfs(i, j, idx, map));
                    idx++;
                }
            }
        }
    }
    static int bfs(int sy, int sx, int mark, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        int size = 1;
        queue.add(new int[]{sy, sx});
        map[sy][sx] = mark;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int dir = 0;dir<4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(ny >= map.length || ny < 0 || nx >= map[0].length || nx < 0 || map[ny][nx]!= 1) continue;
                map[ny][nx] = mark;
                size++;
                queue.add(new int[]{ny, nx});
            }
        }
        return size;
    }
}