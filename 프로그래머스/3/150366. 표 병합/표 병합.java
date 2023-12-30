import java.util.*;
import java.io.*;
// 2023 12 31 01:24
// 2023 12 31
class Solution {
    static int [] p;
    static void makeSet(){
        p = new int[2501];
        for(int i = 0;i<2501;i++){
            p[i] = i;
        }
    }
    static String [] values;
    static int find(int a){
        if(p[a] == a) return p[a];
        return p[a] = find(p[a]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        if(!has(aRoot) && has(bRoot)){
            p[aRoot] = bRoot;
        }else if(has(aRoot) && !has(bRoot)){
            p[bRoot] = aRoot;
        }else if(has(aRoot) && has(bRoot)){
            p[bRoot] = aRoot;
        }else{
            p[bRoot] = aRoot;
        }
        
        return true;
    }
    static boolean has(int a){
        return values[a] != null && !"".equals(values[a]);
    }
    static int [][] info;
    static public String[] solution(String[] commands) {
        // 단순 구현
        // 병합된 정보를 포함할 자료구조가 하나 필요함
        // y, x 가 지금 병합된 상태인지가 중요함
        // 특정 y,x 를 접근했을때 어떤 병합들에 속해있는지 확인해야함
        info = new int[50][50];
        HashMap<Integer, int[]> rev = new HashMap<>();
        int n = 1;
        makeSet();
        for(int i = 0;i<50;i++){
            for(int j = 0;j<50;j++){
                rev.put(n, new int[]{i, j});
                info[i][j] = n;
                n++;
            }
        }
        values = new String[2501];
        ArrayList<String> res = new ArrayList<>();
        for(String comm : commands){
            String [] cm = comm.split(" ");
            int y;
            int x;
            int pos;
            int parent;
            int pinfo;
            switch(cm[0]) {
                case "UPDATE":
                	if(cm.length == 4) {
                		
                        y = Integer.parseInt(cm[1])-1;
                        x = Integer.parseInt(cm[2])-1;
                        String val = cm[3];
                        pos = info[y][x];
                        if(p[pos] == pos){
                            // 병합 안되있는 상태라면 그냥 넣어주면 된다.
                            values[pos] = val;
                        }else{
                            // 자기자신을 가리키고 있지 않다면 어딘가 병합된 상태이다.
                            parent = find(pos);
                            int [] ppos = rev.get(parent);
                            pinfo = info[ppos[0]][ppos[1]];
                            values[pinfo] = val;
                        }
                	}else if(cm.length == 3) {
                		String changed = cm[1];
                		String willChange = cm[2];
                		for(int i = 1;i<2501;i++) {
                			find(i);
                		}
                		for(int i = 1;i<2501;i++) {
                			if(has(p[i]) && values[p[i]].equals(changed)) {
                				values[p[i]] = willChange;
                			}
                		}
                	}
                	
                    break;
                case "MERGE":
                    int p1y = Integer.parseInt(cm[1])-1;
                    int p1x = Integer.parseInt(cm[2])-1;
                    int p2y = Integer.parseInt(cm[3])-1;
                    int p2x = Integer.parseInt(cm[4])-1;
                    if(p1y == p2y && p1x == p2x) continue;
                    int info1 = info[p1y][p1x];
                    int info2 = info[p2y][p2x];
                    union(info1, info2);
                    break;
                case "UNMERGE":
                    y = Integer.parseInt(cm[1])-1;
                    x = Integer.parseInt(cm[2])-1;
                    pos= info[y][x];
                    parent = find(pos);
                    String parentValue = values[parent];
                    for(int i = 1;i<2501;i++){
                        find(i);
                    }
                    for(int i = 1;i<2501;i++){
                        if(p[i] == parent){
                            p[i] = i;
                            values[p[i]] = null;
                        }
                    }
                    values[pos] = parentValue;
                    break;
                default:
                    y = Integer.parseInt(cm[1])-1;
                    x = Integer.parseInt(cm[2])-1;
                    pos = info[y][x];
                    parent = find(pos);
                    if(has(parent)){
                        res.add(values[parent]);
                    }else{
                        res.add("EMPTY");
                    }
                    break;
            }
            
        }
        
        
        
        String[] answer = new String[res.size()];
        for(int i = 0;i<res.size();i++){
            answer[i] = res.get(i);
        }
        return answer;
    }
}