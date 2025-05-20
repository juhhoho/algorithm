package LIS;

import java.util.*;

public class boj11053 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int[] dp = new int[n];
        dp[0] = 1;

        for(int i = 1; i < n; i++){
            int cur = arr[i];

            int max = 0;
            for(int j = i-1; j >= 0; j--){
                if (arr[j] < cur){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }

        int result = dp[0];
        for(int i = 1; i < n; i++){
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
