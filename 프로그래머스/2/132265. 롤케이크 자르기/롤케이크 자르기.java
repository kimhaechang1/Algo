import java.util.*;

class Solution {
    public int solution(int[] topping) {
        // 또다른 사람과 나눠 먹음
        // 각 조각별로 올려진 토핑의 개수와는 상관 X
        // 단순히 종류의 수가 같으면 OK
        int answer = 0;
        int [] cntLeft = new int[10001];
        int [] cntRight = new int[10001];
        // 기준점 1번
        // 기준점 이상부터 끝번까지 right
        // 기준점-1부터 0번까지 left
        int p = 1;
        int right = 0;
        for(int i= p;i<topping.length;i++){
            if(cntRight[topping[i]] == 0) right++;
            cntRight[topping[i]]++;
        }
        int left = 0;
        for(int i= 0;i<p;i++){
            if(cntLeft[topping[i]] == 0) left++;
            cntLeft[topping[i]]++;
        }
        if(left == right){
            answer++;
        }
        while(p < topping.length-1){
            p++;
            if(cntLeft[topping[p-1]] == 0){
                
                left++;
            }
            cntLeft[topping[p-1]]++;
            if(cntRight[topping[p-1]] -1 == 0){
                right--;
            }
            cntRight[topping[p-1]]--;
            if(left == right){
                answer++;
            }
        }
        return answer;
    }
    static void print(int [] arr){
        for(int i= 0;i<10;i++){
            System.out.print("i: "+i+" cnt: "+arr[i]+" ");
        }
        System.out.println();
    }
}