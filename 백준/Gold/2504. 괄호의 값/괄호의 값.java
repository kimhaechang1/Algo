import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String[] t = bf.readLine().split("");
		//System.out.println(Arrays.toString(t));
		Stack<String> stack =new Stack<>();
		boolean isError = false;
		int result = 0;
		for(String str  : t) {
			if(str.equals("(") ||str.equals("[")) {
				stack.push(str);
			}else {
				if(str.equals(")")) {
					int sum = 0;
					while(true) {
						if(stack.isEmpty() || (stack.peek()).equals("[")) {
							isError = true;
                            System.out.println(0);
                            return;
							//break;
						}
						if((stack.peek()).equals("(")) {
							stack.pop();
							if(sum == 0) {
								stack.push(String.valueOf(2));
							}else {
								stack.push(String.valueOf(sum*2));
							}
							break;
						}else {
							String number = stack.pop();
							sum+=Integer.parseInt(number);
						}
					}
				}else if(str.equals("]")) {
					int sum = 0;
					while(true) {
						if(stack.isEmpty() || (stack.peek()).equals("(")) {
							isError = true;
                            System.out.println(0);
                            return;
							//break;
						}
						if((stack.peek()).equals("[")) {
							stack.pop();
							if(sum == 0) {
								stack.push(String.valueOf(3));
							}else {
								stack.push(String.valueOf(sum*3));
							}
							break;
						}else {
							String number = stack.pop();
							sum+=Integer.parseInt(number);
						}
					}
				}
				
			}
			if(isError) {
				break;
			}
		}
		while(!stack.isEmpty()) {
			String poped = stack.pop();
			if(poped.equals("(") || poped.equals("[")) {
				isError = true;
				break;
			}else {
				result+=(Integer.parseInt(poped));
			}
		}
		if(isError) {
			System.out.println(0);
		}else {
			System.out.println(result);
		}

	}

}