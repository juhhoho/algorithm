package implementation;

import java.util.*;

public class boj17836 {
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();

        int[] swordLocation = new int[2];

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    swordLocation[0] = i;
                    swordLocation[1] = j;
                }
            }
        }

        boolean isRight1 = true;
        boolean isRight2 = true;

        // 1. (0,0) -> (n,m)
        int route1 = bfs(n, m, 0, 0, n-1, m-1);
        if (route1 == -1) isRight1 = false;

        // 2, (0,0) -> sword + sword -> (n,m)
        int route2 = bfs(n, m, 0, 0, swordLocation[0], swordLocation[1]);
        if (route2 == -1) isRight2 = false;
        int route3 = (n-1) - swordLocation[0] + (m-1) - swordLocation[1];



        if (isRight1 && !isRight2 && route1 <= t){
            System.out.println(route1);
        }
        else if (!isRight1 && isRight2 && route2 + route3 <= t){
            System.out.println(route2 + route3);
        }
        else if (isRight1 && isRight2 &&  Math.min(route1, route2 + route3) <= t){
            System.out.println(Math.min(route1, route2 + route3));
        }
        else{
            System.out.println("Fail");
        }

    }

    public static int bfs(int n, int m, int row, int col, int dest_row, int dest_col){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col, 0});

        boolean[][] visited = new boolean[n][m];
        visited[row][col] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();

            if (cur[0] == dest_row && cur[1] == dest_col) return cur[2];

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] != 1){
                    queue.offer(new int[]{nr, nc, cur[2]+1});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }
}
