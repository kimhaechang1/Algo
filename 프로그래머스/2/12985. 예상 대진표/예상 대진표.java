class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        // 8 -> 4 -> 2
        // 1,2 3,4 5,6, 7,8
        // 결국 2로 계속 나누면서 영역내에 존재하는지 검사해야한다.
        // 처음 2로 나누었을 때 분리되어 있다면 무조건 2로 나눈 몫-1이 답이다.
        // 다음 2로 나누었을 때 분리되어 있다면 2로 나눈 몫 -2가 답이다.
        // ... 언제까지? 더이상 분리할 수 없는 2개가 될때 까지
        int s = 1;
        int e = n;
        int round = 1;
        while(true){
            int value = (int)(Math.pow(2, round));
            if(value == n){
                break;
            }
            round++;
        }
        
        while(true){
            int middle = (s+e) / 2;
            if(((e - s) + 1) == 2){
                answer = 1;
                break;
            }
            boolean isALeft = false;
            boolean isBLeft = false;
            if(a <= middle){
                isALeft = true;
            }
            if(b <= middle){
                isBLeft = true;
            }
            if(isALeft != isBLeft){
                answer = round;
                break;
            }else{
                if(isALeft){
                    e = middle;
                }else{
                    s = middle+1;
                }
            }
            round--;
        }
        // 1,4 

        return answer;
    }
}