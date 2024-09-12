import java.util.*;

class Solution {
    static class Point {
        public int y;
        public int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean equals(Object o){
            if(!(o instanceof Point)) return false;
            Point p = (Point) o;
            return y == p.y && x == p.x;
        }

        public int hashCode() {
            return 0;
        }
    }
    static ArrayList<int[]> []list;
    static int rn;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        rn = routes.length;
        list = new ArrayList[rn];
        for(int i = 0;i<rn;i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0;i<rn;i++) {
            for(int j = 0;j<routes[i].length-1;j++) {
                ArrayList<int[]> rts =new ArrayList<>();
                int[] ss = points[routes[i][j] - 1];
                int[] tt = points[routes[i][j+1] - 1];
                int sy = ss[0]-1;
                int sx = ss[1]-1;
                int ty = tt[0]-1;
                int tx = tt[1]-1;
                if(j == 0) {
                    list[i].add(new int[]{sy, sx});
                }
                
                while(true) {
                    if (sy < ty) {
                        sy += dy[1];
                    } else if (sy > ty) {
                        sy += dy[0];
                    } else {
                        break;
                    }
                    rts.add(new int[]{sy, sx});
                }
                while(true) {
                    if (sx < tx) {
                        sx += dx[3];
                    } else if (sx > tx) {
                        sx += dx[2];
                    } else {
                        break;
                    }
                    rts.add(new int[]{sy, sx});
                }
                //;
                list[i].addAll(rts);
            }
        }


        HashMap<Integer, ArrayList<Point>> timeTable = new HashMap<>();
        for(int i = 0;i<rn;i++) {
            // System.out.println("# i: "+i);
            for(int j = 0;j<list[i].size();j++) {
                ArrayList<Point> llist = timeTable.get(j);
                if(llist == null) llist = new ArrayList<>();
                int[] pp = list[i].get(j);
                // System.out.print(Arrays.toString(pp));
                llist.add(new Point(pp[0], pp[1]));
                timeTable.put(j, llist);
            }
            System.out.println();
        }
        for(Integer time: timeTable.keySet()) {
            HashMap<Point, Integer> countMap = new HashMap<>();
            for(Point p: timeTable.get(time)) {
                countMap.put(p, countMap.getOrDefault(p, 0) + 1);
            }
            for(Map.Entry<Point, Integer> entry: countMap.entrySet()) {
                if (entry.getValue() > 1 ){
                    answer++;
                }
            }
        }
        return answer;
    }
}