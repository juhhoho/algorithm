package backtracking;

import java.util.*;

public class boj14888 {
    static int n, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] arr;
    static int[] operators;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = sc.nextInt();
        }
        operators = new int[4];
        for(int i = 0 ; i < 4; i++){
            operators[i] = sc.nextInt();
        }

        backtrack(1, arr[0]);

        System.out.println(max + " \n" + min);
    }

    public static void backtrack(int depth, int curValue){
        if (depth == n){
            min = Math.min(min, curValue);
            max = Math.max(max, curValue);
            return;
        }

        for(int i = 0 ; i < 4; i++){
            if (operators[i] > 0){

                int newValue = curValue;

                if (i == 0) newValue += arr[depth];
                else if (i == 1) newValue -= arr[depth];
                else if (i == 2) newValue *= arr[depth];
                else newValue /= arr[depth];

                operators[i]--;
                backtrack(depth + 1, newValue);
                operators[i]++;
            }
        }
    }
}
/*
1 2 3 4
1 + 2


 */