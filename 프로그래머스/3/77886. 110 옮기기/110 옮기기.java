// 0과 1로 이루어진 어떤 문자열 x
// x에 있는 110을 뽑아서 임의의 위치에 다시 삽입한다.
// 사전순으로 가장 앞에 오는 문자열을 만든다.
// 옮기는것이기 때문에 사전순으로 가장 앞에올라면, 앞에 자리수부터 char 비교상 앞서야한다.
// 문자열 길이에 변화는 없다.
// 가능한한 0을 앞쪽으로 땡기는게 좋지 않을까?

// s의 모든 원소의 길이의 합 이 100만이다 -> 어쨋든 100만개의 문자열을 고르게 분포한다는 것
// 110을 찾아서, 적절한 위치에 삽입하는것
// 삽입 위치는, 원래 해당자리수에 1이 아니라 0이 오게 만들 수 있는 위치?

// 0 1 1 1 1 1 1 0 1 0
// 110 추출 후
// 0 1 1 1 1 1 0 
// 110 추출 후 (110 * 2)
// 0 1 1 0 1 1 0 1 1 1

// 원소들 중에서 110을 먼저 추출해낸 결과를 만들고 
// 앞에서 부터 111이 나오는 구간이 존재하면 그 첫번째 인덱스에 110을 삽입한다.

// 토론
// 앞에서 마지막 0 뒤에 넣어야지

import java.util.*;

class Solution {
    static int zzoCnt;
    static final char EMPTY = '2';
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        int index = 0;
        for(String str: s) {
            zzoCnt = 0;
            char[] exts = extract(str);
            int findIdx = -1;
            for(int i = exts.length - 1; i  > -1 ; i--) {
                if (exts[i] == EMPTY) continue;
                if (exts[i] == '0') {
                    findIdx = i;
                    break;
                }
            }
            StringBuilder sb =new StringBuilder();
            if (findIdx == -1) {
                for(int i = 0;i<zzoCnt;i++) {
                    sb.append("110");
                }
                for(int i = 0;i<exts.length;i++) {
                    if (exts[i] == EMPTY) continue;
                    if (exts[i] == '\u0000') break;
                    sb.append(exts[i]);
                }
            } else {
                for(int i = 0;i<=findIdx;i++) {
                    sb.append(exts[i]);
                }
                for(int i = 0;i<zzoCnt;i++) {
                    sb.append("110");
                }
                for(int i = findIdx + 1;i<exts.length;i++) {
                    if (exts[i] == EMPTY) continue;
                    if (exts[i] == '\u0000') break;
                    sb.append(exts[i]);
                }
            }
            answer[index++ ] = sb.toString().replaceAll("\u0000", ""); // 공백 제거할 것
        }
        return answer;
    }
    
    static char[] extract(String target) {
        // 110 찾기
        char[] ts = target.toCharArray();
        char[] result = new char[ts.length];
        for(int i = 0;i<Math.min(target.length(), 2);i++) {
            result[i] = ts[i];
        }
        int idx = 2;
        
        
        for(int i = 2;i<target.length();i++) {
            if (idx > 1 && result[idx - 2] == '1' && result[idx - 1] == '1' && ts[i] == '0'){
                zzoCnt++;
                result[idx - 2] = EMPTY;
                result[idx - 1] = EMPTY;
                result[idx] = EMPTY;
                idx -= 2;
            } else {
                result[idx++] = ts[i];
            }
        }
        
        return result;
    }
}