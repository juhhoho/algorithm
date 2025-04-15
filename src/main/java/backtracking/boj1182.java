package backtracking;

import java.util.*;

public class boj1182 {
    static int n, s, count = 0;
    static int[] arr;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();

        arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = sc.nextInt();
        }

        backtrack(0, 0, false);

        System.out.println(count);
    }

    public static void backtrack(int depth, int curSum, boolean isUsed){
        if (depth == n){
            if (curSum == s && isUsed){
                count++;
            }
            return ;
        }

        backtrack(depth+1, curSum, isUsed);
        backtrack(depth+1, curSum + arr[depth], true);
    }

}

/*
순서가 중요하지 않거나
순서를 강제할 수 있는 방법이 있는 경우는
visited 배열이 필요없음
 */