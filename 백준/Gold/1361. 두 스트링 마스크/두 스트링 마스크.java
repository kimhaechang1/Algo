import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static String S1;
    static String S2;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S1 = bf.readLine();
        S2 = bf.readLine();

        // 완전 탐색
        int starIdx1 = S1.indexOf("*");
        int starIdx2 = S2.indexOf("*");

        String s1left = S1.substring(0, starIdx1);
        String s2left = S2.substring(0, starIdx2);

        String s1right = S1.substring(starIdx1+1);
        String s2right = S2.substring(starIdx2+1);


        String s1 = s1left + s1right;
        String s2 = s2left + s2right;

        ArrayList<String> s1List = new ArrayList<>();
        ArrayList<String> s2List = new ArrayList<>();


        for(int i = 0;i<s2.length();i++) {
            for(int j = i;j<=s2.length();j++) {
                String sub = s2.substring(i, j);
                s1List.add(s1left + sub + s1right);
            }
        }
        s1List.add(s1);

        for(int i = 0;i<s1.length();i++) {
            for(int j = i;j<=s1.length();j++) {
                String sub = s1.substring(i, j);
                s2List.add(s2left + sub + s2right);
            }
        }
        s2List.add(s2);

        int minLength = 987654321;
        String ans = "";
        for(int i = 0;i<s1List.size();i++) {
            for(int j = 0;j<s2List.size();j++) {
                if (s1List.get(i).equals(s2List.get(j)) && minLength > s1List.get(i).length()) {
                    ans = s1List.get(i);
                    minLength = ans.length();
                }
            }
        }
        System.out.println(minLength == 987654321 ? -1 : ans);
    }
}