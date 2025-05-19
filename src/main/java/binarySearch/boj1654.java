package binarySearch;

import java.util.*;

public class boj1654 {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();

        arr = new int[k];
        int max = 0;

        for (int i = 0; i < k; i++) {
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }

        System.out.println(upperBound(1, max + 1) - 1);
    }

    public static int upperBound(int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            int cnt = 0;
            for (int v : arr) {
                cnt += (v / mid);
            }

            if (cnt >= n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
}
