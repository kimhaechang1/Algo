import java.util.*;

class Solution {
    static int max;
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 다른관점에서 살펴보자면 파레메트릭으로도 생각할 수 있음   
        // 정해진 길이 구간내에서 최대의 라운드를 찾는것이라 생각하면 가능할수도 있다.
        // find 메소드는 항상 0라운드부터 mid라운드까지 이고, 
        // 그 라운드속에서 가장 큰값들을 무적권으로 쳐내고 남은 적의 합이 체력보다 많다면 불가능하단것
        // 불가능하면 라운드 수를 낮추면된다.
        if(enemy.length <= k) return enemy.length;
        int s = 0;
        int e = enemy.length-1;
        answer= enemy.length;
        while(s <= e) {
            int mid = (s + e) / 2;
            if(find(mid, enemy, k, n)) {
                s = mid + 1;
            } else {
                answer = Math.min(answer, mid);
                e = mid - 1;
            }
        }
        return answer;
    }
    static boolean find(int target, int[] arr, int k, int n) {
        int[] newArr = new int[target+1];
        for(int i= 0;i<=target;i++) {
            newArr[i] = arr[i];
        } 
        Arrays.sort(newArr);
        long sum = 0;
        for(int i = 0;i<newArr.length - k ;i++) {
            sum += newArr[i];
        }
        return n - sum >= 0;
    }
    
}