package implementation;

import java.util.*;

public class boj20546 {
    static int[] cost = new int[14];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0 ; i < 14; i++){
            cost[i] = sc.nextInt();
        }

        if(Junhyun(n) > Sungmin(n)) System.out.println("BNP");
        else if(Junhyun(n) < Sungmin(n)) System.out.println("TIMING");
        else System.out.println("SAMESAME");
    }

    public static int Junhyun(int n){
        int cnt = 0;
        for(int i = 0; i < 14; i++){
            while (n >= cost[i]) {
                n -= cost[i];
                cnt++;
            }
        }
        return n + cost[13] * cnt;
    }
    public static int Sungmin(int n){
        int cnt = 0;
        for(int i = 3; i < 14; i++){
            int[] arr = new int[]{cost[i-3], cost[i-2], cost[i-1], cost[i]};
            if (buyFlag(arr)){
                while(n >= cost[i]){
                    n -= cost[i];
                    cnt++;
                }
            }
            if (sellFlag(arr)){
                int temp = cnt;
                for(int j = 0; j < temp; j++){
                    n += cost[i];
                    cnt--;
                }
            }
        }
        return n + cost[13] * cnt;
    }

    public static boolean buyFlag(int[] arr) {
        boolean isBuyFlag = true;
        for(int i = 1; i <= 3; i++){
            if (arr[i] >= arr[i-1]){
                isBuyFlag = false;
                break;
            }
        }
        return isBuyFlag;
    }

    public static boolean sellFlag(int[] arr){
        boolean isSellFlag = true;
        for(int i = 1; i <= 3; i++){
            if (arr[i] <= arr[i-1]){
                isSellFlag = false;
                break;
            }
        }
        return isSellFlag;
    }
}