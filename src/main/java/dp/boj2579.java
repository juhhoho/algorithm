package dp;

import java.util.*;

public class boj2579 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] cost = new int[n+1];
        for(int i = 1; i <= n; i++){
            cost[i] = sc.nextInt();
        }

        int[] dp = new int[n+1];
        if (n >= 1) dp[1] = cost[1];
        if (n >= 2) dp[2] = cost[1] + cost[2];
        if (n >= 3) dp[3] = Math.max(cost[1] + cost[3], cost[2] + cost[3]);

        for(int i = 4; i <= n; i++){
            dp[i] = Math.max(dp[i-3] + cost[i-1], dp[i-2]) + cost[i];
        }

        System.out.println(dp[n]);

    }
}

/*
1. 한번에 1계단 or 2계단 점프
2. 연속된 3계단 사용 불가능
3. 마지막 계단은 반드시 사용

dp[i]는 i번째 계단까지 올라올때의 max cost

그럼 i번쨰 계단에 오를수 있는 경우는 i-1 번째 +  i-2 번째니까 이 케이스를 통해 dp[i] 확인

예를 들어 계단이 5개라고 하자.
5번째 계단에 올라갈 수 잇는 케이스는
a. 4에서 5로 1점프
b. 3에서 5로 2점프(3에서 1+1은 전자와 같아져서 제외)

그럼 dp[i] = Math.max(dp[i-1], dp[i-2]) + cost[i] 일까?

여기서 조건2를 추가적으로 고려해야 함.
b케이스의 경우 2계단을 점프하기 때문에 5번쨰 계단에서 조건2에 걸릴 수가 없음
그런데 a케이스의 경우 4번쨰 계단에서 5반쪠 계단으로 1칸 가는 경우 조건2에 걸릴 수도 있음
그럼 4번째 계단에서는 2,3번 계단을 사용했는지를 검증해야됨!
근데 3번째 계단을 사용했다면 결국 b케이스와 같아짐.
그래서 2번째 계단을 사용했는지만 검증하면 되고, 결국 i-3번째 계단에서 i-1, i번째 계단을 사용하는 경우가
1케이스이기 떄문에
dp[i] = Math.max(dp[i-3] + cost[i-1], dp[i-2]) + cost[i]가됨.

 */
