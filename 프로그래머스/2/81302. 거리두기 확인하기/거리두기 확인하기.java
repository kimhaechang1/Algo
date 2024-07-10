import java.util.*;

class Solution {
    static int [] dy = {1,0,-1,1,-1};
    static int [] dx = {0,1,0,1,1};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        // 파티션 X, 사람 P, 빈 테이블 O
        // 맨해튼 거리 |y1 - y2| + |x2 - x1| <= 2
        for(int i = 0;i<places.length;i++){
            go(places[i], answer, i);
        }
        
        
        return answer;
    }
    static void go(String [] mapInfo, int [] ans, int ptr){
        for(int i= 0;i<5;i++){
            for(int j= 0;j<5;j++){
                if(mapInfo[i].charAt(j) == 'P'){
                    if(!check(i, j, mapInfo)){
                        ans[ptr] = 0;
                        return;
                    }
                }
            }
        }
        ans[ptr] = 1;
    }
    static boolean check(int y, int x, String [] maps){
        // 오른쪽으로 2칸, 아래로 2칸, 대각선 아래
        // 4방 검사 -> 여기에는 어떠한 사람도 있어선 안됨
        for(int dir = 0;dir<2;dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny, nx)) continue;
            if(maps[ny].charAt(nx) == 'P') return false;
        }
        for(int dir = 0;dir<2;dir++){
            int ny = y + dy[dir] * 2;
            int nx = x + dx[dir] * 2;
            if(OOB(ny, nx)) continue;
            if(maps[ny].charAt(nx) == 'P'){
                ny -= dy[dir];
                nx -= dx[dir];
                if(maps[ny].charAt(nx) == 'X'){
                    continue;
                }
                return false;
            }
        }
        // 대각선 검사 -> 해당 방향에 사람이 있다면 추가검사해야함
        int yy = y + dy[3];
        int xx = x + dx[3];
        if(!OOB(yy, xx) && maps[yy].charAt(xx) == 'P'){
            for(int dir = 0;dir<2;dir++){
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(maps[ny].charAt(nx) != 'X'){
                    return false;
                }
            }
        }
        // 상하 좌우에 경우에는 겹치는 방향이 있어서 하 우 만 검사하면 되고
        // 대각선의 경우 겹치는 방향으로 인해 하에 대응되는 우측하단, 우에 대응되는 우측 상단만 검사하면됨
        yy = y + dy[4];
        xx = x + dx[4];
        if(!OOB(yy, xx) && maps[yy].charAt(xx) == 'P'){
            for(int dir = 1;dir<3;dir++){
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(maps[ny].charAt(nx) != 'X'){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean OOB(int y, int x){
        return y>= 5 || y < 0 || x >=5 || x < 0;
    }
}