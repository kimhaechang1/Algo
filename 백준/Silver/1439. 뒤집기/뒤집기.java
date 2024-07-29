import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String S = bf.readLine();
        // 0의 연속된 구간 개수
        // 1의 연속된 구간 개수
        // 둘 중 하나라도 0이라면 최소 횟수는 0
        // 둘중 더 적은 것으로 최소횟수를 잡아야 함
        int offCnt = 0;
        int onCnt = 0;
        char bit = S.charAt(0);
        if(bit == '0') offCnt++;
        else onCnt++;
        for(int i= 1;i< S.length();i++){
            if(S.charAt(i) != bit){
                bit = S.charAt(i);
                if(S.charAt(i) == '0'){
                    offCnt++;
                }else{
                    onCnt++;
                }
            }
        }
        int ans = Math.min(offCnt, onCnt);
        System.out.print(ans);
    }
}