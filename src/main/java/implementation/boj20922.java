package implementation;

import java.util.*;

public class boj20922 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int[] used = new int[100_001];

        int max = Integer.MIN_VALUE;

        int left = 0, right = 0;

        while(right < n){
            used[arr[right]]++;

            while(left < right && used[arr[right]] > k){
                used[arr[left]]--;
                left++;

            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        System.out.println(max);
    }
}
