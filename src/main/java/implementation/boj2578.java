package implementation;

import java.util.*;

public class boj2578 {
    static int[][] map = new int[5][5];
    static int cnt = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i < 5; i++){
            for(int j = 0; j < 5; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < 25; i++){
            int n = sc.nextInt();
            erase(n);
        }
    }
    public static void erase(int n){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if (map[i][j] == n){
                    map[i][j] = -1;
                    break;
                }
            }
        }
        cnt++;

        if(bingo()){
            System.out.println(cnt);
            System.exit(0);
        }
    }
    public static boolean bingo(){
        // 가로 검증
        int res = 0;

        for(int i = 0; i < 5; i++){
            int cnt1 = 0;
            for(int j = 0; j < 5; j++){
                if (map[i][j] == -1) cnt1++;
            }
            if (cnt1 == 5) res++;
        }
        // 세로 검증
        for(int i = 0; i < 5; i++){
            int cnt2 = 0;
            for(int j = 0; j < 5; j++){
                if (map[j][i] == -1) cnt2++;
            }
            if (cnt2 == 5) res++;
        }
        // 대각 검증
        // 1. 우상향  2.좌하향
        int cnt3 = 0;
        for(int i = 0; i < 5; i++){
            if (map[i][i] == -1) cnt3++;
        }
        if (cnt3 == 5) res++;

        int cnt4 = 0;
        for(int i = 0; i < 5; i++){
            if (map[4-i][i] == -1) cnt4++;
        }
        if (cnt4 == 5) res++;

        return res >= 3;
    }
}