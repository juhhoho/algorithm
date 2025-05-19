package dp;

import java.util.*;

public class boj1463re {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[1_000_001];
        if (n >= 1) dp[1] = 0;
        if (n >= 2) dp[2] = 1;
        if (n >= 3) dp[3] = 1;

        for(int i = 4; i <= n; i++){
            if (i % 3 == 0 && i % 2 == 0){
                dp[i] = Math.min(Math.min(dp[i/3] + 1, dp[i-1]+1), dp[i/2]+1);
            }
            else if (i % 3 == 0){
                dp[i] = Math.min(dp[i/3] + 1, dp[i-1]+1);
            }
            else if (i % 2 == 0){
                dp[i] = Math.min(dp[i/2] + 1, dp[i-1]+1);
            }
            else dp[i] = dp[i-1] + 1;
        }
        System.out.println(dp[n]);

    }
}
