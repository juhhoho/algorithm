package backtracking;

import java.util.*;

public class boj9663 {
    static int n;
    static int count;
    static int[] arr;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new int[n];

        nQueen(0);
        System.out.println(count);
    }

    public static void nQueen(int depth){
        if (depth == n){
            count++;
            return;
        }

        for(int i = 0; i < n; i++){
            // depth는 행, i는 열
            arr[depth] = i;
            if (isPromising(depth)){
                nQueen(depth+1);
            }
        }
    }

    public static boolean isPromising(int depth){
        for(int i = 0 ; i < depth; i++){

            // 같은 열
            if (arr[i] == arr[depth]){
                return false;
            }

            // 대각선
            if (Math.abs(arr[i] - arr[depth]) == Math.abs(i - depth)){
                return false;
            }
        }
        return true;
    }
}

/*
* * * *
* * * *
* * * *
* * * *

 */
