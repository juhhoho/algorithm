package stack;

import java.util.*;

public class boj3986 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        int cnt = 0;

        for(int i = 0 ; i < n; i++){
            char[] chars = sc.nextLine().toCharArray();
            Stack<Character> stack = new Stack<>();

            for (char ch : chars) {

                if (stack.isEmpty()){
                    stack.push(ch);
                }
                else if (stack.peek() == ch){
                    stack.pop();
                }
                else if (stack.peek() != ch){
                    stack.push(ch);
                }

            }

            if (stack.isEmpty()){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
