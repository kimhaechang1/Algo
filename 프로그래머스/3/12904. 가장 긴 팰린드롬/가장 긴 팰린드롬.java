// 부분 문자열중 가장 긴 팰린드롬
// 팰린드롬? : 앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬 이라고 함
// 팰린드롬이 될려면 결국 어떤 문자기준으로 좌우측 길이가 동일하면서 동일한 변위상에 동일한 char 가 있어야함
// 문자열 길이가 2500이면 N^2까지 가능, 두 포인터로 길이늘리면서 해도 좋을듯
// 팰린드롬 풀 때, 문자열이 홀수인지 짝수인지 잘 생각할 것 
// -> 펠린드롬이라고 가정한 문자열이 짝수인 경우에는 기준 문자열의 길이가 2일수 있다.
class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        char[] crr = s.toCharArray();
        
        for(int i = 1;i<crr.length;i++) {
            int start = i - 1;
            int end = i + 1;
            int len = 1;
            
            if (crr[i] == crr[start]) {
                // 팰린드롬으로 가정한 문자열이 짝수인 경우가 있을 수 있다.
                // 그러면 중앙문자열이 2개여야한다.
                // 그리고 해당 문자열이 입증이 되는순간 이러한 2개의 문자들도 길이 2인 펠린드롬이다.
                answer = Math.max(answer, check(start - 1, end, crr, len + 1));
            } 
            answer = Math.max(answer, check(start, end, crr, len));
        }
        return answer;
    }
    static int check(int s, int e, char[] crr, int l) {
        while(s > -1 && e < crr.length) {
            if (crr[s] != crr[e]) break;
            else {
                l += 2;
                s--;
                e++;
            }
        }
        return l;
    }
}