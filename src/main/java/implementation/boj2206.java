package implementation;

import java.util.*;

public class boj2206 {
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 0 ; j < m; j++){
                map[i][j] = Integer.parseInt(charArray[j] + "");
            }
        }
        int crash = bfs_crash(n, m);
        int def = bfs(n, m);
        if (crash == -1 && def == Integer.MAX_VALUE) System.out.println(-1);
        else if (crash != -1 && def == Integer.MAX_VALUE) System.out.println(crash);
        else if (crash == -1 && def != Integer.MAX_VALUE) System.out.println(def);
        else System.out.println(Math.min(crash, def));

    }

    public static int bfs_crash(int n, int m){
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        // row, col, depth, crash
        queue.offer(new int[]{0, 0, 1, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            System.out.println(Arrays.toString(cur));

            if(cur[0] == n-1 && cur[1] == m-1) return cur[2];

            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc]){
                    if (map[nr][nc] == 0){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2]+1, cur[3]});
                    }
                    else if (map[nr][nc] == 1 && cur[3] < 1){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2]+1, cur[3]+1});
                    }
                }
            }
        }
        return -1;
    }

    public static int bfs(int n, int m){
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        // row, col, depth, crash
        queue.offer(new int[]{0, 0, 1});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            System.out.println(Arrays.toString(cur));

            if(cur[0] == n-1 && cur[1] == m-1) return cur[2];

            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] == 0){
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, cur[2]+1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
