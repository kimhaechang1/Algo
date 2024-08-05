import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String before = bf.readLine();
        char[] cnt = new char[26];
        char[] frags = before.toCharArray();
        for(char frag: frags){
            cnt[frag-'A']++;
        }
        // 홀수냐 짝수냐로 나뉘는듯
        // 짝수면 모든 요소의 알파벳이 무조건 짝수개가 등장해야함
        // 전체 길이가 홀수라면, 홀수개가 단 하나만 등장해야함 그래야 1개 가운데용 으로 빼고 나머지 모두 짝수개여야 함.
        int len = before.length();
        boolean flag = true;
        if (len % 2 == 0){
            for(int i= 0;i<26;i++){
                if(cnt[i] == 0) continue;
                if (cnt[i] % 2 != 0) {
                    flag = false;
                    break;
                }
            }
        } else {
            int oneCnt = 0;
            for(int i= 0;i<26;i++){
                if(cnt[i] == 0) continue;
                if (cnt[i] % 2 ==1) {
                    oneCnt++;
                }
            }
            if (oneCnt > 1) {
                flag = false;
            }
        }
        // AAAABCD


        if (!flag) {
            System.out.println("I'm Sorry Hansoo");
        } else {
            // 가운데용 알파벳을 찾아야 한다.
            StringBuilder sb =new StringBuilder();
            StringBuilder sb2 =new StringBuilder();
            for (int i= 0;i<26;i++){
                if(cnt[i] % 2 != 0){
                    sb2.append((char)(i + 'A'));
                    cnt[i]--;
                    break;
                }
            }
            for (int i= 0;i<26;i++){
                if(cnt[i] == 0) continue;
                if(cnt[i] % 2 == 0){
                    int c = cnt[i] / 2;
                    while(c-- > 0){
                        sb.append((char)(i + 'A'));
                    }
                }
            }
            System.out.println(sb.toString()+ sb2.toString() + sb.reverse().toString());
        }
    }
}