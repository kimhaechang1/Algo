// start 15:18
// end

class Solution {
    public String solution(String[] survey, int[] choices) {
        // R T
        // C F
        // J M
        // A N
        String [] toAnswer = {"RT", "CF", "JM","AN"};
        int [] cnt = new int[26];
        String answer = "";
        int middle = 4;
        for(int i = 0;i<survey.length;i++){
            char [] lr = survey[i].toCharArray();
            if(choices[i] > middle){
                cnt[lr[1]-'A'] += (choices[i] - middle);
            }else if(choices[i] < middle){
                cnt[lr[0]-'A'] += (middle - choices[i]);
            }
        }
        StringBuilder sb =new StringBuilder();
        if('A' < 'B'){
            System.out.println("fdasfadsf");
        }
        for(String t : toAnswer){
            char [] types = t.toCharArray();
            // System.out.println(types[0]+" : "+cnt[types[0]-'A']+", "+types[1]+" : "+cnt[types[1]-'A']);
            if(cnt[types[0]-'A'] > cnt[types[1]-'A']){
                sb.append(types[0]);
            }else if(cnt[types[0]-'A'] < cnt[types[1]-'A']){
                sb.append(types[1]);
            }else{
                
                if(types[0] < types[1]){
                    sb.append(types[0]);
                }else if(types[0] > types[1]){
                    sb.append(types[1]);
                }
            }
        }
        answer = sb.toString();
        return answer;
    }
}