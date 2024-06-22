class Solution {
    static int [][] map;
    static int R;
    static int C;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        R = rows;
        C = columns;
        int cnt = 1;
        map = new int[rows][columns];
        for(int i= 0;i<R;i++){
            for(int j=0;j<C;j++){
                map[i][j] = cnt++;
            }
        }
        answer = new int[queries.length];
        for(int i = 0;i<queries.length;i++){
            answer[i] = rotate(queries[i]);
        }
        return answer;
    }
    static int rotate(int [] info){
        int r1 = --info[0], c1 = --info[1], r2 = --info[2], c2 = --info[3];
        int temp = map[r1][c1]; //-> map[r1][c1+1]에 꽂아야함
        int min = temp;
        for(int i = r1;i<r2;i++){
            map[i][c1] = map[i+1][c1];
            min = Math.min(map[i][c1], min);
        }
        for(int i = c1;i<c2;i++){
            map[r2][i] = map[r2][i+1];
            min = Math.min(map[r2][i], min);
        }
        for(int i = r2;i>r1;i--){
            map[i][c2] = map[i-1][c2];
            min = Math.min(map[i][c2], min);
        }
        for(int i = c2;i>c1+1;i--){
            map[r1][i] = map[r1][i-1];
            min = Math.min(map[r1][i], min);
            
        }
        map[r1][c1+1] = temp;
        return min;
    }
}