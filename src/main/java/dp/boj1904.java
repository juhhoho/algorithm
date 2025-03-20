package dp;

import java.util.*;

public class boj1904 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n+1];
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2] % 15746;
        }
        System.out.println(dp[n]);
    }
}

/*
[00, 1]
크기 5:
* * * * 1
* * * 0 0
* * * 1 1
11개? - > 8개
00001
00100
00111
10000
10011
11001
11100
11111



크기: 4 -> 5
0011
0000
1001
1100
1111


크기: 3 -> 3
001
100
111

크기


 */
