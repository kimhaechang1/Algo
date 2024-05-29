class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int count = 0;
        int cnt = 0;
        while(true){
            if(s.length() == 1){
                break;
            }
            cnt++;
            int before = s.length();
            s = s.replaceAll("0", "");
            count += (before - s.length());
            s = Integer.toBinaryString(s.length()); // int to binary
        }
        answer[0] = cnt;
        answer[1] = count;
        return answer;
    }
}