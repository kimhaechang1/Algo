import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int select = nums.length/2;
        Set<Integer> set = new HashSet<>();
        for(int a : nums) set.add(a);
        answer = set.size() >= select ? select : set.size();
        return answer;
    }
}