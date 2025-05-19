package binarySearch;

import java.util.*;

public class boj1789 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++) arr1[i] = sc.nextInt();

        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for(int i = 0; i < m; i++) arr2[i] = sc.nextInt();


        Arrays.sort(arr1);

        for(int i = 0; i < m; i++){
            int target = arr2[i];

            if(isIn(arr1, 0, arr1.length-1, target)) System.out.println(1);
            else System.out.println(0);
        }
    }
    public static boolean isIn(int[] arr, int start, int end, int target){
        if (start > end || target > arr[arr.length-1] || target < arr[0]) return false;

        int midIndex = (start + end) / 2;
        int midValue = arr[midIndex];

        if (target == midValue) return true;
        else if (target < midValue) return isIn(arr, start, midIndex-1, target);
        else return isIn(arr, midIndex + 1, end, target);
    }
}
