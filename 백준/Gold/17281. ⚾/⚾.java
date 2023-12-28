import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int [][] map; // 각 선수가 각 이닝에서 얻는 결과
    static StringTokenizer stk;
    static int [] res;
    static boolean [] v;
    static int max;
    public static void main(String[] args) throws Exception{
        // 안타 1 2루타 2 3루타 3 홈런 4 아웃 0
        //  각 이닝에서는 아웃을 기록하는 타자가 적어도 한명 존재한다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][10];
        for(int i= 0;i<N;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j= 1;j<10;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 1번선수를 4번타자로 결정내림
        // 나머지 번 선수들은 어떤 순서로 매겨질지에 따라 달라짐
        // 각 선수가 가지고있는 기량으로
        // 선수들 번호마다 뽑아낸 점수가 있고
        // 어떠한 순서대로 경기를 했을 때 3아웃이 될때까지는 다음 이닝으로 넘어가지 않는다.
        // 4번타자를 1로 고정하여 순열을 뽑고 시뮬레이션을 돌려보자.
        v = new boolean[10];
        res = new int[9];
        max = Integer.MIN_VALUE;
        res[3] = 1;
        v[1] = true;
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth){
        if (depth == 3){
            dfs(depth+1);
            return;
        }
        if(depth == 9){
            /*if(res[1] == 2)
                System.out.println(Arrays.toString(res));*/
            int next = 0;
            int score = 0;
            for(int i= 0;i<N;i++){
                boolean [] used = new boolean[10];
                int out = 0;
                int [] ru = new int[4];
                while(true) {
                    if(next == 9) next = 0;
                    if(used[res[next]]) next++;
                    if (map[i][res[next]] == 0) {
                        out++;

                    }
                    else if (map[i][res[next]] == 1){


                        used[res[next]] = true;
                        if(ru[3] > 0){
                            score++;
                            used[ru[3]] = false;
                            ru[3] = 0;
                        }
                        if(ru[2] > 0){
                            ru[3] = ru[2];
                            ru[2] = 0;
                        }
                        if(ru[1] > 0){
                            ru[2] = ru[1];
                            ru[1] = 0;
                        }
                        ru[1] = res[next];

                    }
                    else if (map[i][res[next]] == 2){


                        used[res[next]] = true;
                        if(ru[3] > 0){
                            score++;
                            used[ru[3]] = false;
                            ru[3] = 0;
                        }
                        if(ru[2] > 0){
                            score++;
                            used[ru[2]] = false;
                            ru[2] = 0;
                        }
                        if(ru[1] > 0){
                            ru[3] = ru[1];
                            ru[1] = 0;
                        }
                        ru[2] = res[next];


                    }else if (map[i][res[next]] == 3){


                        used[res[next]] = true;
                        if(ru[3] > 0){
                            score++;
                            used[ru[3]] = false;
                            ru[3] = 0;
                        }
                        if(ru[2] > 0){
                            score++;
                            used[ru[2]] = false;
                            ru[2] = 0;
                        }
                        if(ru[1] > 0){
                            score++;
                            used[ru[1]] = false;
                            ru[1] = 0;
                        }
                        ru[3] = res[next];


                    }else if (map[i][res[next]] == 4){


                        if(ru[3] > 0){
                            score++;
                        }
                        if(ru[2] > 0){
                            score++;
                        }
                        if(ru[1] > 0){
                            score++;

                        }
                        used[ru[1]] = used[ru[2]] = used[ru[3]] = false;
                        ru[1] = ru[2] = ru[3] = 0;
                        score++;

                    }


                    if (out == 3) {
                        next++;
                        break;
                    }
                    next++;
                }

            }

            if(max < score){
                max = score;
            }
            return;
        }

        for(int i= 1;i<=9;i++){

            if(!v[i]){
                if(i == 1) continue;
                v[i] = true;
                res[depth] = i;
                dfs(depth+1);
                v[i] = false;
            }
        }
    }
}
