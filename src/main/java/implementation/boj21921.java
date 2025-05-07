package implementation;

import java.util.*;

public class boj21921 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = sc.nextInt();
        }


        int initSum = 0;
        for(int i = 0; i < m; i++){
            initSum += arr[i];
        }

        int max = initSum;
        int maxCnt = 1;
        for(int i = 1; i <= n - m; i++){
            initSum -= arr[i-1];
            initSum += arr[i -1 + m];
            if (initSum > max){
                max = initSum;
                maxCnt = 1;
            }
            else if (initSum == max) {
                maxCnt++;
            }
        }
        if (max == 0) System.out.println("SAD");
        else System.out.println(max + "\n" + maxCnt);
    }
}
