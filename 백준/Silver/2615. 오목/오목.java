import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 제출확인용 5
public class Main {

    public static void main(String[] args) throws Exception {
        //////////////////////////////////////////////////////////////
        // 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
        //////////////////////////////////////////////////////////////
//        System.setIn(new FileInputStream("Test5.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] board = new int[19][19];
        
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

//        for(int i = 0; i < 19; i++)
//            System.out.println(Arrays.toString(board[i]));
        
        int[] dx = { 1, 1, 0, -1 };
        int[] dy = { 0, 1, 1, 1 };
        
        int win = 0;
        int[] winp = new int[2];
        
        int chk = 0;
        int cnt = 0;
        int step = 0;
        L : for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {

                if(board[i][j] != 0) {// 
                    chk = board[i][j];
                    
                    
//                    System.out.println(i + " " + j);
                    for(int dir = 0; dir < 4; dir++) {
                        step = 1;    
                        cnt = 1;
                        while(true) {
                            int nx = i + dx[dir] * step;
                            int ny = j + dy[dir] * step;
                            
//                            System.out.println(":::: " +nx + " " + ny);
                            
                            step++;
                            if(nx < 0 || ny < 0 || 19 <= nx || 19 <= ny)
                                break;
                            if(chk != board[nx][ny])
                                break;
                            
                            cnt++;
                        }
                        step = 1;
                        while(true) {
                            int nx = i + -(dx[dir] * step);
                            int ny = j + -(dy[dir] * step);
                            
//                            System.out.println(":::: " +nx + " " + ny);
                            
                            step++;
                            if(nx < 0 || ny < 0 || 19 <= nx || 19 <= ny)
                                break;
                            if(chk != board[nx][ny])
                                break;
                            
                            cnt++;
                        }
                        
                        if(cnt == 5) {
//                            System.out.println("clear");
                            win = chk;
                            step = 0;
                            while(true){
                                int nx = i + -(dx[dir] * step);
                                int ny = j + -(dy[dir] * step);
                                if(nx < 0 || ny < 0 || 19 <= nx || 19 <= ny)
                                    break;
                                if(board[nx][ny] != chk)
                                    break;
                                winp[0] = nx + 1;
                                winp[1] = ny + 1;
                                step++;
                            }
                            break L;
                        }
                    }
                }
            }
        }
        
        System.out.println(win);
        if(win != 0)
            System.out.println(winp[0] + " " + winp[1]);

//        1
//        3 2

        
    }
}