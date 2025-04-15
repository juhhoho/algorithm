package backtracking;
import java.util.*;

public class boj14889 {
    static int n;
    static int[][] map;
    static boolean[] visited;
    static int minStat = Integer.MAX_VALUE;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];
        visited = new boolean[n];

        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        backtrack(0, 0);
        System.out.println(minStat);
    }

    public static void backtrack(int index, int depth){
        if (depth == n/2){
            calDiff();
            return;
        }
        for(int i = index; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void calDiff(){
        int start = 0, link = 0;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if (visited[i] && visited[j]) {
                    start += map[i][j];
                    start += map[j][i];
                }
                if (!visited[i] && !visited[j]) {
                    link += map[i][j];
                    link += map[j][i];
                }
            }
        }
        minStat = Math.min(minStat, Math.abs(start - link));
        if (minStat == 0){
            System.out.println(minStat);
            System.exit(0);
        }
    }
}
/*
1       2      3
2 3 4   1 3 4
 */