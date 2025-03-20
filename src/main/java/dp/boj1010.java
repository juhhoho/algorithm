package dp;

import java.util.*;

public class boj1010 {
    static int[][] dp = new int[30][30];  // DP 테이블

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        // 바텀업 방식으로 DP 테이블 미리 계산
        for (int i = 0; i < 30; i++) {
            dp[i][0] = 1; // (iC0 = 1) → n개 중에서 0개를 고르는 경우는 항상 1
            dp[i][i] = 1; // (iCi = 1) → n개 중에서 n개를 고르는 경우는 항상 1
        }

        // DP 테이블 채우기 (조합 점화식 사용)
        for (int i = 1; i < 30; i++) {  // m 값
            for (int j = 1; j < i; j++) {  // n 값 (n <= m)
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];  // 점화식 적용
            }
        }

        // 2. 입력을 받아 결과 출력
        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();  // 서쪽 사이트 개수
            int m = sc.nextInt();  // 동쪽 사이트 개수

            System.out.println(dp[m][n]);  // 미리 계산된 값 출력
        }
    }
}
