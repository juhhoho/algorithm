package dp;

import java.util.*;

public class boj9095re {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[] dp = new int[11];
        dp[1] = 1; // 1
        dp[2] = 2; // 1+1, 2
        dp[3] = 4; // 1+1+1, 1+2, 2+1, 3
        dp[4] = 7; // 1+1+1+1, 1+2+1, 2+1+1, 3+1, 1+1+2, 2+2, 1+3

        for(int i = 5; i <= 10; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i = 0; i < t; i++){
            int n = sc.nextInt();
            System.out.println(dp[n]);

        }
    }
}
/*
1 -> 1
2 -> 2
3 -> 4
4 -> 7
5 -> 13
6 -> 24
7 -> 44
8 -> 81
9 -> 149
10 -> 274
 */