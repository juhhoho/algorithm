package twopointer;

import java.util.*;

public class boj2018 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];

        for(int i = 0; i <= n; i++){
            arr[i] = i;
        }

        int left = 1, right = 1, cur = 0, cnt = 0;

        while(right < n + 1){
            cur += arr[right];

            while(cur > n && left < right){
                cur -= arr[left];
                left++;
            }

            if (cur == n) {
                //System.out.println("(" +left + ", " + right + ")");
                cnt++;
            }

            right++;
        }
        System.out.println(cnt);
    }
}
