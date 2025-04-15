package twopointer;

import java.util.*;

public class boj1940 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n-1;

        int cur = 0;
        int cnt = 0;

        while(left < right){
            cur = arr[left] + arr[right];
            if (cur > m) right--;
            else if (cur < m) left++;
            else {
                cnt++;
                left++;
                right--;
            }
        }
        System.out.println(cnt);
    }
}
