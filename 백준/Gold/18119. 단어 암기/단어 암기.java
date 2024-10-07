import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;

    static String[] queries;

    static int n;
    static int q;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        q = Integer.parseInt(stk.nextToken());
        StringBuilder sb = new StringBuilder();
        int[] origin = new int[n];
        int bit = 0;
        for(int i = 0;i<n;i++) {
            String word = bf.readLine();
            int bitSet = 0;
            for(char alpha: word.toCharArray()) {
                bit |= getShiftBit(alpha);
                bitSet |= getShiftBit(alpha);
            }
            origin[i] = bitSet;
        }

        while(q-- > 0) {
            stk = new StringTokenizer(bf.readLine());
            int flag = Integer.parseInt(stk.nextToken());
            char target = stk.nextToken().charAt(0);
            int shift = getShiftBit(target);
            int cnt = 0;
            if (flag == 1) {
                bit &= (~shift);
            } else {
                bit |= (shift);
            }
            for(int i = 0;i<n;i++){
                if ((origin[i] & bit) == origin[i]) cnt++;
            }
            sb.append(cnt).append("\n");
            // 18119
        }
        System.out.print(sb);
    }
    static int getShiftBit(char target) {
        int targetIdx = (int)target - 'a';
        int targetBit = 1;
        for(int j = 0;j<targetIdx;j++) {
            targetBit <<= 1;
        }
        return targetBit;
    }
    static void bitPrint(int bitSet) {
        int bit = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<26;i++) {
            if ((bitSet & bit) != 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            bit <<= 1;
        }
        System.out.print(sb);
    }
}