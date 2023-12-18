import java.util.*;
import java.io.*;

public class Main{
    static int [][] map;
    static int n;
    static int m;
    static StringTokenizer stk;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static class Group{
        int cnt;
        ArrayList<int[]> list;
        int y;
        int x;
        int rcnt;
        public Group() {}
        public Group(int cnt, ArrayList<int[]> list, int y, int x, int rcnt) {
            this.cnt = cnt;
            this.y = y;
            this.x = x;
            this.list = list;
            this.rcnt = rcnt;
        }
		@Override
		public String toString() {
			return "Group [cnt=" + cnt + ", list=" + list + ", y=" + y + ", x=" + x + ", rcnt=" + rcnt + "]";
		}
        
    }
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][n];
        for(int i = 0;i<n;i++) {
            stk =new StringTokenizer(bf.readLine());
            for(int j = 0;j<n;j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 일반블색은 :  m 이하의 자연수
        // 검정 : -1, 무지개 0
        // 상하좌우 인접칸
        // 블록그룹은 일반 블록이 최소 1개 이상 있어야함, 그룹내 모든 색이 같아야 한다.
        // 다른 자연수 색 그룹에는 검정은 포함하면 안되며, 무지개는 얼마든지 포함 가능
        // 그룹에 속한 블록의 개수는 2이상
        // 그룹 내에 블록은 한 덩어리어야 한다.
        // 해당 덩어리 즉 그룹내에 기준블록은 무지개 블록이 아닌 블록중 왼쪽위
        // 시뮬레이션
        // 1. 크기가 가장 큰 블록 그룹을 찾기
        // 1-1. 그러한 블록 그룹이 많다면, 포함된 무지개 블록이 많은 블록
        // 1-2. 그것도 여러개라면 기준블록의 오른쪽 아래
        // 2. 찾은 블록그룹의 모든 믈록을 제거, 제거 후 B^2 스코어 휙득
        // 3. 격자 중력 작용
        // 4. 격자 90도 반시계
        // 5. 격자 다시중력 작용
        // 여기서 말하는 중력작용은 검은 블록을 제외한 모든 블록이 떨어지는것
        // 언제까지 ? 다른 블록이나 경계를 만날때 까지
        // 블록에 그룹이 존재할때까지 반복
        int s = 0;
        while(chk()) {
            Group group = findMax();
            s += getScore(group);
            go();
            rotate();
            go();
        }
        System.out.println(s);
    }
    static boolean chk() {
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(map[i][j] <= 0) continue;
                int val = map[i][j];
                for(int k = 0;k<4;k++) {
                    int ny = i+dy[k];
                    int nx = j+dx[k];
                    if(ny >= n || ny < 0 || nx >= n || nx < 0 || map[ny][nx] == -1) continue;
                    if(map[ny][nx] == 0 || map[ny][nx] == val) return true;
                }
            }
        }
        return false;
    }
    static Group findMax(){
        Group max = new Group();
        boolean [][] v= new boolean[n][n];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(map[i][j] == -2) continue;
                if(map[i][j] <= 0) continue;
                Group g = getGroup(i,j, v);
                if(g.cnt < 2) {
                    continue;
                }
                if(max.cnt < g.cnt) {
                    max = g;
                }else if(max.cnt == g.cnt) {
                    if(max.rcnt < g.rcnt) {
                        max = g;
                    }else if(max.rcnt == g.rcnt) {
                        if(max.y < g.y) {
                            max = g;
                        }else if(max.y == g.y) {
                            if(max.x < g.x) {
                                max = g;
                            }
                        }
                    }
                }
            }
        }
        return max;
    }
    static Group getGroup(int sy, int sx, boolean [][] v) {
        Queue<int[]> queue = new ArrayDeque<>();
        ArrayList<int []> pos =new ArrayList<>();
        v[sy][sx] = true;
        pos.add(new int[] {sy, sx});
        int val = map[sy][sx];
        queue.add(new int[] {sy, sx });
        Queue<int []> zeros = new ArrayDeque<>();
        while(!queue.isEmpty()) {
            int [] now = queue.poll();
            for(int k = 0;k<4;k++) {
                int ny = now[0] + dy[k];
                int nx = now[1] + dx[k];
                if(ny >= n || ny < 0 || nx >= n || nx < 0 || v[ny][nx] || map[ny][nx] == -1) continue;
                if(map[ny][nx] == 0) {
                    v[ny][nx] = true;
                    zeros.add(new int[] {ny, nx});
                    queue.add(new int[] {ny, nx});
                }else if(map[ny][nx] == val) {
                    v[ny][nx] = true;
                    queue.add(new int[] {ny,nx});
                    pos.add(new int[] {ny, nx});
                }
            }
        }
        Collections.sort(pos, (o1, o2)->{
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int [] gi = pos.get(0);
        int rcnt = zeros.size();
        while(!zeros.isEmpty()) {
            int [] now = zeros.poll();
            pos.add(new int[] {now[0], now[1]});
            v[now[0]][now[1]] = false;
        }
        Group res = new Group(pos.size(), pos, gi[0], gi[1], rcnt);
        return res;
    }
    static int getScore(Group group) {
        int c = group.cnt * group.cnt;
        for(int [] pos : group.list) {
            map[pos[0]][pos[1]] = -2;
        }
        return c;
    }
    static void go() {
        for(int i = 0;i<n;i++) {
            Queue<int []> queue = new ArrayDeque<>();
            for(int j = n-2;j>-1;j--) {
                if(map[j][i] <= -1) continue;
                queue.add(new int[] {j, i, map[j][i]});
                map[j][i] = -2;
                
            }
            while(!queue.isEmpty()) {
                
                int size = queue.size();
                for(int j = 0;j<size;j++) {
                    int [] now = queue.poll();
                    int ny = now[0] + dy[1];
                    int nx = now[1] + dx[1];
                    if(map[ny][nx] >= -1) {
                        map[now[0]][nx] = now[2];
                    }
                    else if(ny == n-1) {
                        map[ny][nx] = now[2];
                    }else {
                        queue.add(new int[] {ny, nx, now[2]});
                    }
                }
            }
        }
    }
    static void rotate() {
        int [][] temp = new int[n][n];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                temp[n-1-j][i] = map[i][j];
            }
            
        }
        for(int i = 0;i<n;i++) {
            map[i] = temp[i].clone();
        }
        
    }
    static void print() {
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                if(map[i][j] == -2) System.out.print("\t");
                else System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("============");
    }
}