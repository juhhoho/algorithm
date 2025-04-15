package implementation;

import java.util.*;

public class boj14501 {
    static int n;
    static int max = 0;
    static int[][] cost;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][2];
        for(int i = 0; i < n; i++){
            cost[i][0] = sc.nextInt();
            cost[i][1] = sc.nextInt();
        }

        backtrack(0, 0);
        System.out.println(max);
    }

    public static void backtrack(int depth, int sum){
        if(depth == n){
            max = Math.max(max, sum);
            return ;
        }

        backtrack(depth+1, sum);
        if (depth + cost[depth][0] <= n)
            backtrack(depth+cost[depth][0], sum + cost[depth][1]);

    }
}
