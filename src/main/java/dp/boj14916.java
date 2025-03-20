package dp;

import java.util.*;

public class boj14916 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];

        // 초기값 설정
        Arrays.fill(dp, Integer.MAX_VALUE); // 큰 값으로 초기화
        dp[0] = 0; // 0원을 만들기 위한 동전 개수는 0
        if (n >= 2) dp[2] = 1; // 2원 → 2원 하나
        if (n >= 5) dp[5] = 1; // 5원 → 5원 하나

        // DP 테이블 채우기 (Bottom-Up)
        for (int i = 1; i <= n; i++) {
            if (i >= 2 && dp[i - 2] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            }
            if (i >= 5 && dp[i - 5] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }

        // n원을 만들 수 없는 경우
        if (dp[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n]);
        }
    }
}


