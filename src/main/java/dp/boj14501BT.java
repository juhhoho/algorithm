package dp;

import java.util.*;

public class boj14501BT {

    static Task[] arr;
    static int max = 0;
    static class Task{
        int cost, reward;
        public Task(int c, int r){
            this.cost = c;
            this.reward = r;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new Task[n];
        for(int i =0; i < n; i++) {
            int c = sc.nextInt();
            int r = sc.nextInt();
            arr[i] = new Task(c, r);
        }

        backtrack(0, 0); // cur, depth

        System.out.println(max);
    }

    public static void backtrack(int cur, int depth){
        if (depth >= arr.length){
            if (depth == arr.length) {
                max = Math.max(max, cur);
            }
            return;
        }

        backtrack(cur+arr[depth].reward, depth + arr[depth].cost);
        backtrack(cur, depth + 1);
    }
}
