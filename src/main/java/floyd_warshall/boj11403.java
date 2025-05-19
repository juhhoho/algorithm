package floyd_warshall;

import java.util.*;

public class boj11403 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                map[i][j] = sc.nextInt() == 1 ? 1 : Integer.MAX_VALUE;
            }
        }
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if (map[i][k] < Integer.MAX_VALUE && map[k][j] < Integer.MAX_VALUE && map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++) {
                if (map[i][j] < Integer.MAX_VALUE) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
}
