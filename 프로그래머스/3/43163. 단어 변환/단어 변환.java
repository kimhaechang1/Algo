class Solution {
    static boolean [] v;
    static int N;
    static int min;
    static int d;
    static String [] arr;
    static String t;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        N = words.length;
        v = new boolean[N];
        arr = new String[N];
        for(int i= 0;i<N;i++){
            arr[i] = words[i];
        }
        t = target;
        d = begin.length();
        min = Integer.MAX_VALUE;
        dfs(0, begin);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    static void dfs(int depth, String pres){
        if(min < depth) return;
        if(pres.equals(t)){
            min = Math.min(depth, min);
            return;
        }
        for(int i= 0;i<N;i++){
            if(v[i]) continue;
            char [] p = pres.toCharArray();
            char [] a = arr[i].toCharArray();
            int c = 0;
            boolean flg = true;
            for(int j = 0;j<d;j++){
                if(p[j] != a[j]){
                    c++;
                    if(c >= 2){
                        flg = false;
                        break;
                    }
                }
            }
            if(!flg) continue;
            v[i] = true;
            String temp = new String(pres);
            pres = new String(arr[i]);
            dfs(depth+1, pres);
            pres = new String(temp);
            v[i] = false;
        }
    }
}