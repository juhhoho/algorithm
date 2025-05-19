package binarySearch;

import java.util.*;

public class boj10816 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Arrays.sort(arr);

        int m = sc.nextInt();
        for(int i = 0; i < m; i++){
            int target = sc.nextInt();
            System.out.print((upperBound(arr, target) - lowerBound(arr, target)) + " ");
        }
    }

    public static int upperBound(int[] arr, int target){
        int begin = 0;
        int end = arr.length;

        while(begin < end){
            int midIndex = (begin+end)/2;

            if (arr[midIndex] <= target) begin = midIndex+1;
            else end = midIndex;
        }
        return end;
    }

    public static int lowerBound(int[] arr, int target){
        int begin = 0;
        int end = arr.length;

        while(begin < end){
            int midIndex = (begin + end)/2;

            if (arr[midIndex] >= target) end = midIndex;
            else begin = midIndex+1;
        }
        return end;
    }

}
//0   1   2 3 4 5 6 7  8  9
//-10 -10 2 3 3 6 7 10 10 10
