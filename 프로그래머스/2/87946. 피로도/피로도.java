import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        // 순열 백트래킹 문제
        int N = dungeons.length;
        int [] arr = new int[N];
        for(int i = 0;i<N;i++){
            arr[i] = i;
        }
        int max = -1;
        do{
            int r = go(arr, k, dungeons);
            if(max < r) max = r;
        }while(np(arr));
        answer = max;
        return answer;
    }
    static boolean np(int [] arr){
        int i = arr.length-1;
        while(i > 0 && arr[i-1] > arr[i]) i--;
        if(i == 0) return false;
        int j = arr.length-1;
        while(arr[i-1] > arr[j]) j--;
        int tmp = arr[j];
        arr[j] = arr[i-1];
        arr[i-1] = tmp;
        int k = arr.length-1;
        while(i < k){
            tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
            i++;
            k--;
        }
        return true;
    }
    static int go(int [] arr, int k, int [][] d){
        int cnt = 0;
        int idx = 0;
        while(k > 0 && idx < d.length){
            if(idx == d.length) break;
            int need = d[arr[idx]][0];
            int dis = d[arr[idx]][1];
            if(k < need) break;
            k-=dis;
            idx++;
            cnt++;
        }
        return cnt;
    }
}