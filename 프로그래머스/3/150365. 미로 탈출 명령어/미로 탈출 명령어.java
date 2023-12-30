import java.util.*;
import java.io.*;

// 2023 12 31 2:50
// 2023 12 31

class Solution {
    static String resValue;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static boolean [][][] v;
    static char [] res;
    static boolean isFind;
    static void dfs(int depth, int n, int m, int y, int x, int r, int c, int k){
        if(isFind){
            return;
        }
        if((Math.abs(r - y) + Math.abs(c - x)) > k - depth) return;
        if(depth == k){
            if(y == r && x == c){
                isFind = true;
                StringBuilder sb = new StringBuilder();
                for(char ch : res){
                    sb.append(ch);
                }
                resValue = sb.toString();
                
            }
            return;
        }
        // d l r u : 하 좌 우 상
        
        
        if(!isFind && !OOB(y + dy[1],x + dx[1],n,m) && !v[y + dy[1]][x+dx[1]][depth+1]){
            char temp = res[depth];
            res[depth] = 'd';
            y = y + dy[1];
            x = x + dx[1];
            depth += 1;
            v[y][x][depth] = true;
            dfs(depth, n, m, y, x, r, c, k);
            v[y][x][depth] = false;
            depth -=1;
            y = y - dy[1];
            x = x - dx[1];
            res[depth] = temp;
        }
        if(!isFind && !OOB(y + dy[2],x + dx[2],n,m)  && !v[y + dy[2]][x+dx[2]][depth+1]){
            char temp = res[depth];
            res[depth] = 'l';
            y = y + dy[2];
            x = x + dx[2];
            v[y][x][depth] = true;
            
            depth += 1;
            dfs(depth, n, m, y, x, r, c, k);
            depth -= 1;
            v[y][x][depth] = false;
            
            y = y - dy[2];
            x = x - dx[2];
            res[depth] = temp;
        }
        if(!isFind && !OOB(y + dy[3],x + dx[3],n,m)  && !v[y + dy[3]][x+dx[3]][depth+1]){
            char temp = res[depth];
            res[depth] = 'r';
            y = y + dy[3];
            x = x + dx[3];
            depth += 1;
            v[y][x][depth] = true;
            dfs(depth, n, m, y, x, r, c, k);
            v[y][x][depth] = false;
            depth -= 1;
            y = y - dy[3];
            x = x - dx[3];
            res[depth] = temp;
        }

        if(!isFind && !OOB(y + dy[0],x + dx[0],n,m)  && !v[y + dy[0]][x+dx[0]][depth+1]){
            char temp = res[depth];
            res[depth] = 'u';
            y = y + dy[0];
            x = x + dx[0];
            depth += 1;
            v[y][x][depth] = true;
            dfs(depth, n, m, y, x, r, c, k);
            v[y][x][depth] = false;
            depth -= 1;
            y = y - dy[0];
            x = x - dx[0];
            res[depth] = temp;
        }
        
    }
    public static String solution(int n, int m, int y, int x, int r, int c, int k) {
        isFind = false;
        String answer = "";
        resValue = "";
        v = new boolean[n][m][k+1];
        res = new char[k];
        int dis = Math.abs((y - 1) - (r - 1)) + Math.abs((x - 1) - (c - 1));
        if(dis > k || ((k-dis)) % 2 == 1){
            answer = "impossible";
        }else{
            dfs(0, n, m, y-1, x-1, r-1, c-1, k);    
        }
        answer = has(resValue) ? resValue : "impossible";
        return answer;
    }
    static boolean OOB(int y, int x, int n, int m){
        return y >= n || y < 0 || x >= m || x < 0;
    }
    static boolean has(String str){
        return str!=null && !"".equals(str);
    }

    
}