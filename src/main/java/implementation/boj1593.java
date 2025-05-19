package implementation;

import java.util.*;

public class boj1593 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int w =  sc.nextInt();
        int g = sc.nextInt();
        String w_word = sc.next();
        String g_word = sc.next();

        int[] std = new int[52];
        for(int i = 0; i < w; i++){
            int cur = w_word.charAt(i);

            if (65 <= cur && cur <= 90)
                std[cur-65]++;
            else
                std[cur-97+26]++;
        }

        int[] alpha = new int[52];
        for(int i = 0; i < w-1; i++){
            int cur = g_word.charAt(i);

            if (65 <= cur && cur <= 90)
                alpha[cur-65]++;
            else
                alpha[cur-97+26]++;
        }

        int cnt = 0;

        for(int i = w-1; i < g; i++){
            int cur = g_word.charAt(i);

            // 새로운 알파벳 개수 증가
            if (65 <= cur && cur <= 90)
                alpha[cur-65]++;
            else
                alpha[cur-97+26]++;

            // w_word랑 검증
            if (isMatch(std, alpha)) cnt++;

            // 맨 앞 알파벳 개수 감소
            int prevW = g_word.charAt(i - (w-1));
            if (65 <= prevW && prevW <= 90)
                alpha[prevW-65]--;
            else
                alpha[prevW-97+26]--;

        }
        System.out.println(cnt);
    }

    public static boolean isMatch(int[] std, int[] alpha){
        for(int i = 0; i < 52; i++){
            if (std[i] != alpha[i]) return false;
        }
        return true;
    }
}
