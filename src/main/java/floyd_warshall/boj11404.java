package floyd_warshall;

import java.util.*;

public class boj11404 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n+1][n+1];
        int inf = Integer.MAX_VALUE/2;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if (i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = inf;
            }
        }

        int m = sc.nextInt();
        for(int i = 0 ; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();
            if(map[s][e] > w)
                map[s][e] = w;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(map[i][j] == inf)
                    System.out.print(0 + " ");
                else
                    System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}