package implementation;

import java.util.*;

public class boj1182 {
    static int n, m, cnt = 0;
    static int[] arr;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        backtrack(0, 0, false);
        System.out.println(cnt);
    }
    public static void backtrack(int depth, int cur, boolean isUsed){
        if(depth == n){
            if (cur == m && isUsed) cnt++;
            return;
        }

        backtrack(depth+1, cur + arr[depth], true);
        backtrack(depth+1, cur , isUsed);
    }

}
