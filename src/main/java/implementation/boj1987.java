package implementation;

import java.util.*;

public class boj1987 {

    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n;
    static int m;
    static int max = Integer.MIN_VALUE;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        
        map = new char[n][m];
        for(int i = 0; i < n; i++){
            char[] row = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                map[i][j] = row[j];
            }
        }
        int[] used = new int[26];
        used[map[0][0]-65] = 1;

        backtrack(used, 0, 0, 1);

        System.out.println(max);

    }

    public static void backtrack(int[] used, int cr, int cc, int total){
        max = Math.max(max, total);

        for(int i = 0; i < 4; i++){
            int nr = cr + dr[i];
            int nc = cc + dc[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m){
                if (used[map[nr][nc] - 65] > 0) continue;
                used[map[nr][nc] - 65]++;
                backtrack(used, nr, nc, total+1);
                used[map[nr][nc] - 65]--;
            }
        }
    }
}
