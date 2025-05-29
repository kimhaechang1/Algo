import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // S 는 -, M 은 *, U는 정수몫 나눗셈, P는 +, C는 여태까지 결괏값 반환

        // 3 - 2 * 3 / 1 + 2
        StringBuilder sb = new StringBuilder();
        char[] signs = str.toCharArray();
        // 일단 알파벳을 만나는 경우에 현재까지 측정한 길이의 숫자를 평가해야한다.
        // 평가 한 뒤에 연산자 스택에 알파벳이 있는경우 누적 피연산자 값과 연산해야 한다.
        // 만약 연산자 스택에 알파벳이 없는 경우에는, 누적 피연산자를 그대로 더해야한다.
        // C를 만나면 StringBuilder에 출력값을 넣어야하고
        int prev = 0;
        StringBuilder digitBuilder = new StringBuilder();
        char exp = 'P';
        int result = 0;
        for(int i = 0; i < signs.length; i++) {
            if (isDigit(signs[i])) {
                digitBuilder.append(signs[i]);
            } else {
                if (digitBuilder.length() != 0) {
                    int val = Integer.parseInt(digitBuilder.toString());
                    result = eval(result, val, exp);
                }

                digitBuilder.setLength(0);
                if (exp == 'C') {
                    sb.append(result).append(" ");
                }
                exp = signs[i];
            }
        }
        if (exp == 'C') {
            sb.append(result).append(" ");
        }

        if (sb.length() == 0) {
            sb.append("NO OUTPUT");
        }

        System.out.print(sb);
    }
    boolean isDigit(char ch) {
        return ch - '0' >= 0 && ch - '0' <= 9;
    }

    int eval(int a, int b, char exp) {

        if (exp == 'S') {
            return a - b;
        } else if (exp == 'M') {
            return a * b;
        } else if (exp == 'U') {
            return a / b;
        } else {
            return a + b;
        }
    }


    void input() throws Exception {

        n = Integer.parseInt(bf.readLine());
        str = bf.readLine();
    }
}