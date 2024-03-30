// start 14:39
// end 
class Solution {
    public int solution(int n) {
        // 1 2 3 4 5 6 7 8 9 10
        // 1 2 4 5 7 8 10 11 14 16
        // 다음 수가 3의 배수가 나올 자리라면 +1 
        // 수를 +1을 해서 피했더니 3이 끼여있으면 또 +1
        int answer = 0;
        for(int i=1;i<=n;i++){
            ++answer;
            while(check(answer)){
                answer++;
            }
        }
        
        
        return answer;
    }
    static boolean check(int number){
        if(number >= 3 && number % 3 == 0){
            return true;
        }
        char [] numberFrags = String.valueOf(number).toCharArray();
        for(int i= 0;i<numberFrags.length;i++){
            if(numberFrags[i] == '3'){
                return true;
            }
        }
        return false;
    }
}