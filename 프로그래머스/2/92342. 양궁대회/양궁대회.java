import java.util.*;

class Solution {
    static boolean canWin;
    static int[] result;
    static int N;
    static int max;
    static int[] ansArr;
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        // 어피치가 화살 n발을 다 쏜 후에 라이언이 화살 N발을 쏨
        N = n;
        result= new int[N];
        ansArr = new int[11];
        // 점수는 11개로 0 ~ 10점
        // 무조건 지거나 비기는 경우 -1
        // 라이언이 가장 큰 점수차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지
        // 앞에서부터 10점짜리 몇개 맞췄는지를 나타낸다.
        // 완탐: 0~ 10 사이의 중복조합으로 가능할듯 // 화살을 최대 10발만 쏨
        canWin = false;
        max = 0;
        dfs(0, info, 0);
        if (!canWin) {
            return new int[]{-1};
        }
        return ansArr;
    }
    static void dfs(int depth, int[] a, int start) {
        if(depth == N) {
            int[] score = new int[11];
            for(int i = 0;i<N;i++) {
                score[10 - result[i]]++;
            }
            int aScore= 0;
            int rScore= 0;
            for(int i= 0;i<11;i++) {
                if(score[i] + a[i] == 0) continue;
                if(score[i] <= a[i]) {
                    aScore += (10 - i);
                } else {
                    rScore += (10 - i);
                }
            }
            if(rScore > aScore) {
                canWin = true;
                int cha = rScore - aScore;
                if (max < cha) {
                    max = cha;
                    ansArr = score.clone();
                } else if (max == cha) {
                    for(int i = 10 ;i>-1;i--) {
                        if (ansArr[i] < score[i]) {
                            // 아 이미 더 낮은 점수에서 더 많이 획득한게 보이면 갱신 할 필요가 없다.
                            ansArr = score.clone();
                            return;
                        } else if (ansArr[i] > score[i]) {
                            return;
                        }
                    }
                }
            }
            return;
        }
        
        for(int i = start;i<=10;i++) {
            result[depth] = i;
            dfs(depth+1, a, i);
        }
    }
}