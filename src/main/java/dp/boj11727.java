package dp;

import java.util.*;

public class boj11727 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[1001];
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 3;
        if (n >= 3) dp[3] = 5;
        if (n >= 4) dp[4] = 11;

        for(int i = 5; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2]*2)%10007;
        }
        System.out.println(dp[n]);
    }
}

/*
1. 1, |
2. 3, || = ㅁ
3. 5,
|||(2)
=| (2)
ㅁ|(2)
|=
|ㅁ

4. 11,
|||| (3)
|=| (3)
=|| (3)
|ㅁ| (3)
ㅁ|| (3)

||= (2)
||ㅁ (2)
ㅁㅁ (2)
ㅁ=  (2)
=ㅁ  (2)
==  (2)


 */