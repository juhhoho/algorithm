package dp;

import java.util.*;

public class boj14501DP {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 총 날짜

        int[] t = new int[n]; // 상담 기간
        int[] p = new int[n]; // 상담 보상
        int[] dp = new int[n + 1]; // i일까지 최대 이익

        for(int i = 0; i < n; i++){
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            // 1. i번째 상담을 진행하는 경우
            if (i + t[i] <= n) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }

            // 2. i번째 상담을 하지 않는 경우 (다음 날로 넘기기)
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[n]); // 마지막 날까지의 최대 보상
    }
}
