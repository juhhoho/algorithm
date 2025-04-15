package dp;

import java.util.*;

public class boj1038 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // dp[i]는 i번째수가 감소하는 수인지 아닌지
        List<int[]> dp = new ArrayList<>();
        // boolean[] dp = new boolean[n+1]; // index, isDown[true: 1, false: -1], order
        for(int i = 0; i <= 9; i++){
            // dp[i] = true;
            dp.add(new int[]{i, 1, i});

        }
        if (n < 10) {
            System.out.println(n);
            return;
        }

        int order = 9;

        for(int i = 10; i <= 1000000; i++){
            // before, lastOfBefore, after
            String s = i + "";
            String before = s.substring(0, s.length()-1);
            String lastOfBefore = before.isEmpty() ? "0" : before.charAt(before.length()-1) + "";
            String after = s.substring(s.length()-1, s.length());

            int b = Integer.parseInt(before);
            int lb = before.isEmpty() ? 0 : Integer.parseInt(before.substring(before.length() - 1));
            int a = Integer.parseInt(after);

            if (dp.get(b)[1] == -1 || lb <= a)
                dp.add(new int[]{i, -1, -1});
            else
                dp.add(new int[]{i, 1, ++order});

        }
        for(int i = 10; i < dp.size();i++){
            if (dp.get(i)[2] == n){
                System.out.print(dp.get(i)[0]);
                return;
            }
        }
        System.out.print(-1);
    }
}

/*
31234 + 2에 대해서
Integer.
 */
