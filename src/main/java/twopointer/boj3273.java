package twopointer;

import java.util.*;

public class boj3273 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int target = sc.nextInt();

        int left = 0, right = n -1, cur = 0, cnt = 0;

        while(left < right){
            cur = arr[left] + arr[right];

            if (cur > target) right--;
            else if (cur < target) left++;
            else {
                cnt++;
                left++;
                right--;
            }
        }
        System.out.println(cnt);


    }
}
