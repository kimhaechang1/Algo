class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        // t초 동안 붕대를 감으면서 1초당 x만큼 회복
        // t초 연속으로 붕대를 감는데 성공하면 y만큼의 체력을 추가로 회복
        // 최대체력을 넘어설순 없음
        // 문제의 숨겨진 조건인 다음공격까지의 텀이 붕대 시전시간보다 훨씬 길때의 처리를 해줬지만
        // 문제는 오버 힐 상태가 일어나는 코드였다.
        
        int max = health;
        health -= attacks[0][1];
        int pres = attacks[0][0];
        
        for(int i = 1;i<attacks.length;i++) {
            if(health <= 0) {
                return -1;
            }
            int time = attacks[i][0];
            int dam = attacks[i][1];
            int duration = time - pres - 1;
            if (duration >= 1) {
                if (duration < bandage[0]) {
                    health += ( duration * bandage[1] );
                    if (health > max) health = max;
                } else {
                    int t = duration / bandage[0];
                    health += ((bandage[0] * bandage[1] + bandage[2]) * t);
                    if (health < max) {
                        health += ( (duration % bandage[0]) * bandage[1] );   
                    }
                    if (health > max) health = max;
                    
                }
            }
            health -= dam;
            pres = time;
        }
        return health <= 0 ? -1 : health;
    }
}