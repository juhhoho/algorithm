package dp;

import java.util.*;

public class boj11053 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] dp = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.fill(dp, 1);

        int count = 0;


        int maxLIS = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if (arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
    }
}
/*
문제 가장 긴 부분 수열!

dp[i] : i번째 인데스까지 접근했을 때 가장 큰 수
count: 증가수열의 길이

i-1이 i 이상이면 못씀 -> dp[i-1]

i-1이 i보다 작으면
dp[i] = cost[i]

dp[i] = Math.max(dp[i-1], cost[i]);
count++;

 */