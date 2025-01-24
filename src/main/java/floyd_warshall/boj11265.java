package floyd_warshall;
import java.util.*;
public class boj11265 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] map = new int[n+1][n+1];
        int inf = Integer.MAX_VALUE/2;
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        
        for(int i = 0 ; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int limit = sc.nextInt();
            if (map[s][e] <= limit)
                System.out.println("Enjoy other party");
            else
                System.out.println("Stay here");
        }
        
    }
}