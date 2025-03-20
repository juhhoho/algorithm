package dp;

import java.util.*;

public class boj2839 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        if (n >= 3) dp[3] = 1;
        if (n >= 5) dp[5] = 1;

        for(int i = 1; i <= n; i++){
            if (i >= 3 && dp[i-3] != Integer.MAX_VALUE){
                dp[i] = Math.min(dp[i], dp[i-3] + 1);
            }
            if (i >= 5 && dp[i-5] != Integer.MAX_VALUE){
                dp[i] = Math.min(dp[i], dp[i-5] + 1);
            }
        }

        if (dp[n] == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[n]);
        }
    }
}
