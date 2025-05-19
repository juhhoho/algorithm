package binarySearch;

import java.util.*;

public class boj10815 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        int m = sc.nextInt();
        for(int i = 0; i < m; i++) {
            int target = sc.nextInt();
            if(isIn(arr, target, 0, n-1)) System.out.print(1 + " ");
            else System.out.print(0 + " ");
        }
    }
    public static boolean isIn(int[] arr, int target, int start, int end){
        if (start > end) return false;

        int midIndex = (start + end) / 2;
        int midValue = arr[midIndex];

        if (midValue == target) return true;
        else if (midValue > target) return isIn(arr, target, start, midIndex-1);
        else return isIn(arr, target, midIndex+1, end);
    }
}
