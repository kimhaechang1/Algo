class Solution
{
    static int n;
    static int m;
    public int solution(int [][]board)
    {
        n = board.length;
        m = board[0].length;
        // dp로 해결해야함
        // 어떠한 점까지의 정사각형의 최대 크기라고 생각해서 bottom-up하면 된다.
        // +1 을 해주는 것은 해당점까지의 최대 정사각형 크기에서 지금 점을 추가하면 길이를 늘려주는 셈이 됨
        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for(int i = 1;i<n+1;i++){
            for(int j= 1;j<m+1;j++){
                if(board[i-1][j-1]!= 0){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                    max = Math.max(max, dp[i][j] * dp[i][j]);
                }
            }
        }
        

        return max;
    }
}