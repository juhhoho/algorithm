package dp;
import java.util.*;

public class boj1912 {
    static int n;
    static int[] arr, dp;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n+1];
        for(int i = 1 ; i <= n; i++){
            arr[i] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;

        dp = new int[n+1];
        if (n >= 1) dp[1] = arr[1];
        max = dp[1];

        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

}
/*
dp[i] 는 기존 부분수열에 i번째 값까지 갔을 때 총 합의 최대 값
만약 i를 사용한다면 dp[i] = dp[i-1] + cost[i]
만약 i를 사용안한다면 i번째부터가 다시 합이 되니까 cost[i]

 */
