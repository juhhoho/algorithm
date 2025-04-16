package implementation;

import java.util.*;

public class boj1806 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++) arr[i] = sc.nextInt();

        int left = 0;
        int right = 0;
        int cur = 0;
        int min = Integer.MAX_VALUE;

        while(right < n){
            cur += arr[right];

            while(cur >= m && left <= right) {
                min = Math.min(min, right - left + 1);
                System.out.printf("left: %d, right: %d -> sum: %d\n", left, right, cur);
                cur -= arr[left];
                left++;
            }

            right++;
        }
        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}

/*
XXX
OO.
XXX

XOX
OXO
XOX

OXO
XOX
OXO
 */