package backtracking;

import java.util.*;

public class boj15651 {
    static int n,m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        backtrack(0, new ArrayList<>());

        System.out.println(sb);
    }

    public static void backtrack(int depth, ArrayList<Integer> curArr){
        if (depth == m){
            for(int ele : curArr){
                sb.append(ele).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= n; i++){
            curArr.add(i);
            backtrack(depth+1, curArr);
            curArr.remove(curArr.size()-1);
        }
    }
}
