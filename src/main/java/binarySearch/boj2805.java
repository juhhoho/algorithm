package binarySearch;

import java.util.*;

public class boj2805 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();

        long[] arr = new long[n];
        long max = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);

        System.out.println(upper(arr, max, m)-1);

    }

    public static long upper(long[] arr, long max, long target){
        long minLen = 1;
        long maxLen = max+1;

        while(minLen < maxLen){
            long midLen = (minLen + maxLen)/2;
            long extra = 0;
            for(long tree: arr)
                extra += Math.max((tree - midLen), 0);

            if (extra >= target)
                minLen = midLen + 1;
            else
                maxLen = midLen;
        }
        return maxLen;
    }
}
