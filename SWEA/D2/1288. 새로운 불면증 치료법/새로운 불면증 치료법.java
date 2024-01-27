import java.util.*;
import java.io.*;
class Solution
{
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1;t<=T;t++){
            String number = bf.readLine();
            long n = Long.parseLong(number);

            long res = n;
            // 비트 확인
            long bit = 0;
            char [] nums  = number.toCharArray();
            for(int i= 0;i<nums.length;i++) {
                bit |= (1 << (nums[i]-'0'));
            }
            // check bit
            long checkBit = ((1 << 10)-1);
            while(true) {
                if((bit & checkBit) == checkBit) {
                    break;
                }
                res += n;
                nums = String.valueOf(res).toCharArray();
                for(int i= 0;i<nums.length;i++) {
                    bit = bit | (1 << (nums[i]-'0'));
                }
            }
            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
        System.out.print(sb);		
	}
	static void check(long bit) {
		for(int i = 0;i<10;i++) {
			if((bit & (1 << i))!= 0) {
				System.out.print(1+" ");
			}else {
				System.out.print(0+" ");
			}
		}
		System.out.println("\n");
	}
}