import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static StringTokenizer stk;
    static int [][] map;
    static class Data implements Comparable<Data>{
        int idx;
        int value;
        public Data(int i, int v){
            idx = i;
            value = v;
        }
        public String toString(){
            return "[idx : "+idx+" value : "+value +"]";
        }
        public int compareTo(Data o){
            if(this.value == o.value){
                return this.idx -  o.idx;
            }
            return this.value - o.value;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        // 좌표의 대소비교만이 필요한 경우 아무리 많은 좌표가 있더라도 압축을 해버리면 된다.
        // 여기서 압축되는 것은 실제로 대소비교가 의미가 있는 좌표 즉, 중복된 원소를 제거하면 된다.
        // 그리고 나서 각 원소별 순서대로 정렬 후 위치를 지정해주면 된다.
        String [] numStr = new String[m];
        int [][] origin = new int[m][n];
        int cnt = 0;
        for(int i  = 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            HashSet<Integer> set = new HashSet<>();
            ArrayList<Integer> pList = new ArrayList<>();
            for(int j = 0;j<n;j++){
                int num = Integer.parseInt(stk.nextToken());
                set.add(num);
                origin[i][j] = num;
                pList.add(num);
            }

            Iterator<Integer> iter = set.iterator();
            StringBuilder sb = new StringBuilder();
            Collections.sort(pList);
            for(int number : origin[i]){
                sb.append(search(number, pList)).append(" ");
            }
            // 어짜피 서로가 중복된 원소가 존재한다면 더 살펴 볼 이유가 없다.
            numStr[i] = sb.toString();
        }
        for(int i = 0;i<m;i++) {
            for (int j = i + 1; j < m; j++) {
                if (numStr[i].equals(numStr[j])) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    static int search(int find, ArrayList<Integer> list){
        int s = 0;
        int e = n;
        int res = 0;
        while(s < e){
            int mid = (s+e)/2;
            if(find > list.get(mid)){
                s = mid+1;
            }else if(find == list.get(mid)){
                res = mid;
                break;
            }else{
                e = mid-1;
            }
        }
        return res;
    }
}
