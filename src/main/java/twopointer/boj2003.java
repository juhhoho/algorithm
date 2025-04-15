package twopointer;

import java.util.*;

public class boj2003 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int left = 0, right = 0, cnt = 0, cur = 0;

        while(right < n){
            cur += arr[right];

            while(cur > m && left < right) {
                cur -= arr[left];
                left++;
            }

            if (cur == m) cnt++;

            right++;
        }
        System.out.println(cnt);

    }
}
