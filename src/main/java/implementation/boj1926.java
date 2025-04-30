package implementation;

import java.util.*;

public class boj1926 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0 ,0 ,-1, 1};

        int max = 0;
        int p_cnt = 0;

        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (!visited[i][j] && map[i][j] == 1){
                    p_cnt++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    visited[i][j] = true;
                    int cnt = 1;
                    while(!queue.isEmpty()){
                        int[] cur = queue.poll();

                        for(int k = 0; k < 4; k++){
                            int nr = cur[0] + dr[k];
                            int nc = cur[1] + dc[k];

                            if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] == 1){
                                queue.offer(new int[]{nr, nc});
                                visited[nr][nc] = true;
                                cnt++;
                            }
                        }
                    }
                    max = Math.max(max, cnt);
                }
            }
        }
        System.out.println(p_cnt + "\n"+ max);


    }
}
