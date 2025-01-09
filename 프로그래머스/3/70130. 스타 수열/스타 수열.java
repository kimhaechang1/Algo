// x의 길이가 2 이상의 짝수
// x의 길이를 2n 이라고 할때, 다음과 같은 n개의 집합의 교집합의 원소의 개수가 1이상
// 길이가 가장 긴 스타수열의 길이를 return

// 스타수열에 있어서 공통되는 값이 무조건 하나이상 있어야 한다.
// 그렇다는것은 cnt에 있어서 최소 2개이상 있어야 하는것
// 그러한 후보 값을 기준으로
// 후보값이 포함된 집합을 찾는데, 자기 자신과 인접한 원소에 대해서 검사하면 된다.

// 위와는 다른 풀이를 생각해보자.
// 애초에 공통원소가 될 교집합 원소는 반드시 cnt가 2회 이상 불리게 되어있다.

// 그리고 어디까지가 집합으로 구성되어있는지 인덱스를 해놓는다면, 다음 집합을 찾을 때에도 수고가 덜어진다.
// 마킹 해놓는거지. 인덱스 번호로 0번이 올 수 있으므로 -1로 마스킹 해놓는다.

// 그리고 각각의 다음 원소에 대해서 이전에 등장한적이 있는지 검사해야한다.
// 그래야 인접하게 들어가지 않을것이기 때문

import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int size = 0;
        int[] cnt = new int[a.length];
        int[] index = new int[a.length];
        Arrays.fill(index, -1);
        for(int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                continue;
            }
            // 각각의 숫자를 사용해서 만들 수 있는 최대 쌍의 개수
            if (index[a[i]] < i) {
                cnt[a[i]]++;
                index[a[i]] = i + 1;
            }
            
            if (index[a[i + 1]] < i) {
                cnt[a[i + 1]]++;
                index[a[i + 1]] = i + 1;
            }
        }
        
        for(int i = 0; i < cnt.length; i++) {
            answer = Math.max(answer, cnt[i]);
        }
         
        return answer * 2;
    }
}