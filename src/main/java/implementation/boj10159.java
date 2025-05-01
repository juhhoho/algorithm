package implementation;

import java.util.*;

public class boj10159 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE/2 -2);
            map[i][i] = 0;
        }

        for(int i = 0 ; i < m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            map[v1][v2] = 1;
        }

        for(int k = 1 ; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if (map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        for(int i = 1; i <= n; i++){
            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if (i == j) continue;
                if (map[i][j] > 10000 && map[j][i] > 10000) cnt++;
            }
            System.out.println(cnt);
        }

    }
}
