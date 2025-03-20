package dp;

import java.util.*;

public class boj1463 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n+1];
        for(int i = 1; i <=n; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        if (n >= 1) dp[1] = 0;
        if (n >= 2) dp[2] = 1;
        if (n >= 3) dp[3] = 1;

        for(int i = 1; i <= n; i++){
            if (i % 2 == 0){
                dp[i] = Math.min(dp[i], dp[i/2]+1);
            }
            if (i % 3 == 0){
                dp[i] = Math.min(dp[i], dp[i/3]+1);
            }
            dp[i] = Math.min(dp[i], dp[i-1]+1);
        }
        System.out.println(dp[n]);
    }
}
/*
<dp 문제 해결 과정>
dp의 핵심은 "현재 상태가 무엇인가" 이다.
이는 곧 dp 테이블을 무엇으로 정의할 것인지와 연결된다.

위 문제에서는 dp 테이블을 통해 3개의 연산을 통해 1을 만들수 있는 최소 횟수가 될 것이다.

그러면 점화식을 세워보자.

우선 초기값을 먼저 정해야한다.
문제 조건에서 주어지는 n이 무조건 1 이상이라고 했으니 1부터 생각해보자.
1. n=1, n이 1이라면 별도의 연산이 필요없으니 dp[1] = 0
2. n=2, n이 2라면 2로 나누는 연산이 필요하니 dp[2] = 1
3. n=3, n이 3이라면 3으로 나누는 연산이 피요하니 dp[3] = 1
즉, 초기값은 일반적으로 간단하므로 본인이 직접 구해서 하드코딩으로 넣어주면 된다.

초기값을 세웠다면 반복되는 초기값을 바탕으로 점화식을 세워야한다.

* i-1
    dp[i] = dp[i-1] + 1
* i/2
    dp[i] = dp[i/2] + 1
* i/3
    dp[i] = do[i/3] + 1

주어진 연산은 총 3가지 인데,,,
예를 들어 n이 4라고 해보자.
4는 2로 나누어 떨어지므로,
dp[4] = dp[4/2] + 1 이고, dp[2] = dp[2/2] + 1 = 2
이런식으로 된다.

 */