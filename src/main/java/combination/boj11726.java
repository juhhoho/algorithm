package combination;

import java.util.*;

public class boj11726 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n+1];
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 2;

        for(int i = 3 ; i <= n; i++){
            dp[i] = ( dp[i-1] + dp[i-2] ) % 10007;
        }
        System.out.println(dp[n]);
    }
}
/*
dp[i] -> 2*i 직사각형을 채울 수 있는 경우의 수

 */