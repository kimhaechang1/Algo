import java.util.*;
import java.io.*;

// start 19:56
// end

public class Main{
    static StringTokenizer stk;
    static int r;
    static int c;
    static int k;
    static int [][] arr;

    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        arr = new int[3][3];
        for(int i = 0;i<3;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0;j<3;j++){
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 1 초마다 연산
        // R연산 : 가로줄마다 정렬을 수행, 행의 개수가 열의 개수 이상인 경우에 적용
        // C연산 : 세로줄마다 정렬을 수행, 열의 개수가 행의 개수보다 많을 때 적용
        // 숫자의 등장 빈도수가 중요함
        // 수의 등장횟수가 커지는 순, 그것이 여러가지라면 수가 커지는 순
        // 정렬된 결과를 넣을 때에는, 수와 등장횟수슬 모두 넣으며, 순서는 수가 먼저
        // 행 또는 열의 크기가 100을 넘어갈때에는 처음부터 100개를 제외하고 짤라버린다.
        // 열의 크기가 혹은 행의 크기가 커진곳에는 0이 채워진다.
        int t = 0;
        boolean find = false;
        while(t <= 100){
            if(r - 1 < arr.length && c - 1 < arr[0].length){
                if(arr[r-1][c-1] == k) {
                    find = true;
                    break;
                }
            }
            int rl = arr.length;
            int cl = arr[0].length;
            if(rl >= cl){
                goR();
            }else{
                goC();
            }
            t++;
        }
        System.out.println(find ? t : -1);
    }
    static void goR(){
        PriorityQueue<Integer> [] pq = new PriorityQueue[arr.length];
        int maxLength = 1;
        int [][] count = new int[arr.length][101];
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr[i].length;j++){
                if(arr[i][j] == 0) continue;
                count[i][arr[i][j]]++;
            }
            int[][] finalCount = count;
            int currentIdx = i;
            pq[i] = new PriorityQueue<>((a, b)->{
                if(finalCount[currentIdx][a] == finalCount[currentIdx][b]){
                    return a - b;
                }
                return finalCount[currentIdx][a] - finalCount[currentIdx][b];
            });
            for(int j = 0;j<=100;j++) {
                if(finalCount[currentIdx][j] == 0) continue;
                pq[i].add(j);
            }
            int l = pq[i].size() * 2;
            maxLength = Math.max(maxLength, l);
        }
        if(maxLength > 100){
            maxLength = 100;
        }
        arr = new int[arr.length][maxLength];
        for(int i = 0;i<arr.length;i++){
            int cursor = 0;
            while(!pq[i].isEmpty() && cursor < maxLength){
                int poll = pq[i].poll();
                arr[i][cursor] = poll;
                cursor++;
                if(cursor < maxLength){
                    arr[i][cursor] = count[i][poll];
                    cursor++;
                }
            }
            while(cursor < maxLength){
                arr[i][cursor] = 0;
                cursor++;
            }
        }
    }
    static void goC(){
        PriorityQueue<Integer> [] result = new PriorityQueue[arr[0].length];
        int [][] count = new int[arr[0].length][101];
        int maxLength = 1;
        for(int i = 0;i<arr[0].length;i++){
            for(int j = 0;j<arr.length;j++){
                if(arr[j][i] == 0) continue;
                count[i][arr[j][i]]++;
            }
            int[][] finalCount = count;
            int currentIdx = i;
            result[i] = new PriorityQueue<>((a, b)->{
                if(finalCount[currentIdx][a] == finalCount[currentIdx][b]){
                    return a - b;
                }
                return finalCount[currentIdx][a] - finalCount[currentIdx][b];
            });
            for(int j = 0;j<=100;j++) {
                if(finalCount[i][j] == 0) continue;
                result[i].add(j);
            }
            int l = result[i].size() * 2;
            maxLength = Math.max(maxLength, l);
        }
        if(maxLength > 100){
            maxLength = 100;
        }
        arr = new int[maxLength][arr[0].length];
        for(int i = 0;i<arr[0].length;i++){
            int cursor = 0;
            while(!result[i].isEmpty() && cursor < maxLength){
                int poll = result[i].poll();
                arr[cursor][i] = poll;
                cursor++;
                if(cursor < maxLength){
                    arr[cursor][i] = count[i][poll];
                    cursor++;
                }

            }
            while(cursor < maxLength){
                arr[cursor][i] = 0;
                cursor++;
            }
        }

    }
}