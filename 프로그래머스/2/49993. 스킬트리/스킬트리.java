// start 21:01
// end 21:19
import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i = 0;i<skill_trees.length;i++){
            char [] skillFrags = skill.toCharArray();
            char [] targets = skill_trees[i].toCharArray();
            boolean isError = false;
            int [] board = new int[skill.length()];
            Arrays.fill(board, 99);
            int idx = 0;
            for(int j= 0;j<skillFrags.length;j++){
                char find = skillFrags[j];  
                for(int k = 0;k<targets.length;k++){
                    if(targets[k] == find){
                        board[j] = k;
                        break;
                    }
                }
            }
            for(int j = 0;j<skill.length()-1;j++){
                if(board[j] > board[j+1]){
                    isError = true;
                    break;
                }
            }
            if(isError) continue;
            answer++;
        }
        return answer;
    }
}