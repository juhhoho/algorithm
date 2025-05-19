package dp;

import java.util.*;

public class boj11726re {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[1001];
        dp[0] = 0;
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 2;
        if (n >= 3) dp[3] = 3;


        for(int i = 4; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
    }
}
/*
1 -> 1 |
2 -> 2 ||, =
3 -> 3 |||, |=, =|
4 -> 5, ||||, ||=, =||, |=|, ==
5 -> 8

 */