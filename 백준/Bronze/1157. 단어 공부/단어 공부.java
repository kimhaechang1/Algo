import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s =bf.readLine();
        s = s.toUpperCase();
        int []num = new int[26];
        char []ch = s.toCharArray();
        for(int k=0;k<s.length();k++){
            char tmp = s.charAt(k);
            for(char i=65;i<91;i++){
                if(tmp==i){
                    num[i-65]++;
                }
            }
        }
        int max=-1;
        char mx='?';
        for( int i=0;i<26;i++){ // num 배열의 최대값을 찾고
            if(num[i]>max){     // 최대값일 때 마다 그때의 인덱스에 해당하는 문자를 mx에 저장
                max = num[i];
                mx = (char)(i+65);
            }else if(num[i]==max){ // 최대값들과 동일한 num배열값이 존재한다면 mx를 ?로 저장
                mx = '?';
            }
        }
        System.out.println(mx);
    }
}