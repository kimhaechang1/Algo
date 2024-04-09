// start 09:28
// end
import java.util.*;

class Solution {
    static int [][] map;
    static int n;
    static int m;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    public int[] solution(String[] maps) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j= 0;j<m;j++){
                char frag = maps[i].charAt(j);
                if(frag - '0' > 0 && frag - '0' < 10){
                    map[i][j] = frag - '0';
                }else{
                    map[i][j] = 0;   
                }
            }
        }
        System.out.println("n : "+n+" m : "+m);
        System.out.println(Arrays.deepToString(map));
        for(int i= 0;i<n;i++){
            for(int j= 0;j<m;j++){
                if(map[i][j] != 0){
                    int result = bfs(i, j);
                    System.out.println("result : "+result);
                    if(result != 0){
                        list.add(result);    
                    }
                }
            }
        }
        Collections.sort(list);
        if(list.size() == 0){
            answer = new int[1];
            answer[0] = -1;
        }else{
            answer = new int[list.size()];
            for(int i= 0;i<list.size();i++){
                answer[i] = list.get(i);
            }
            System.out.print(Arrays.toString(answer));
        }
        return answer;
    }
    static int bfs(int sy, int sx){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sy, sx});
        int sum = map[sy][sx];
        map[sy][sx] = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int dir = 0;dir < 4;dir++){
                int ny= now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || map[ny][nx] <= 0) continue;
                sum += map[ny][nx];
                map[ny][nx] = 0;
                queue.add(new int[]{ny, nx});
            }
        }
        return sum;
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }
}