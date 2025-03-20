package bfs;

import java.util.*;

public class boj1303 {

    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dr = {-1, 1, 0 ,0};
    public static int[] dc = {0, 0, -1, 1};

    public static int bfs(int n , int m, int i, int j, int std){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        int size = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];

            if (!visited[cr][cc]){
                visited[cr][cc] = true;
                size += 1;

                for(int d = 0 ; d < 4; d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] == std){
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return size;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            char[] row = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                if (row[j] == 'W') map[i][j] = 1;
                else map[i][j] = 0;
            }
        }

        visited = new boolean[n][m];
        int resW = 0;
        int resB = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (!visited[i][j]){
                    int v = bfs(n, m, i , j, map[i][j]);

                    if (map[i][j] == 1) resW += (v * v);
                    else resB += (v * v);
                }
            }
        }
        System.out.println(resW + " "  + resB);
    }
}
