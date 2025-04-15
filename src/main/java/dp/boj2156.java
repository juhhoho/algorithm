package dp;

import java.util.*;

public class boj2156 {
    static int n;
    static int[] cost, dp;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        cost = new int[n+1];
        for(int i = 1; i <= n; i++){
            cost[i] = sc.nextInt();
        }

        dp = new int[n+1];

        if (n >= 1) dp[1] = cost[1];
        if (n >= 2) dp[2] = cost[1] + cost[2];
        if (n >= 3) dp[3] = Math.max(cost[1] + cost[2], Math.max(cost[1] + cost[3], cost[2] + cost[3]));


        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + cost[i], dp[i-3] + cost[i-1] + cost[i]));
        }
        System.out.println(dp[n]);
    }
}
/*
dp[i] = i번째 포도주까지 도달했을 때 마신 최대 포도주
그러면 (i-2, i-1, i) 에 대해서 고려를 할 때
000
010
100
110

001
101

011

이렇게 7가지 경우의 수가 나옴. (1,1,1)은 불가능하므로 제외
근데 모든 경우를 점화식에 넣으면 중복이 생길 수 있음

i번째를 마시지 않는 상황을 보자.
i번째를 마시지 않는다는 것은 dp[i-1]과 같기 때문에 사실상 i=0인 모든 케이스는 dp[i-1]과 같다.

다음은 i번째를 마시는 상황이다.
같은 논리로 i-1번째에서 마시지 않는 케이스는 dp[i-2]와 같기 때문에
i-1번째를 안마시고, i번째를 마시는 상황은 dp[i-2] + cost[i]이다.

마지막으로 i-2번쨰를 안마시고, i-1번째를 마시고, i번째를 마시는 상황은
dp[i-3] + cost[i-1] + cost[i]이다.

그러면 dp[i] = Math.max(dp[i-1], dp[i-2] + cost[i], dp[i-3] + cost[i-1] + cost[i])
이렇게 3가지 경우로 압축된다는 것이다,
 */