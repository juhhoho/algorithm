package bfs;

import java.util.*;

public class boj2573 {
    public static int[][] map;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static boolean[][] visited;

    public static int getZeroBlock(int cr, int cc){
        if (map[cr][cc] == 0){
            return 0;
        }

        int cnt = 0;
        for(int i = 0 ; i < 4; i++){
            int nr = cr + dr[i];
            int nc = cc + dc[i];
            if (map[nr][nc] == 0){
                cnt++;
            }
        }
        return cnt;
    }

    public static void bfs(int cr, int cc){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{cr, cc});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(!visited[cur[0]][cur[1]]){
                visited[cur[0]][cur[1]] = true;

                for(int i = 0 ; i < 4; i++){
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if(!visited[nr][nc] && map[nr][nc] > 0){
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int days = 1;
        while(true) {
            int[][] tempMap = new int[n + 1][m + 1];

            for (int i = 2; i <= n - 1; i++) {
                for (int j = 2; j <= m - 1; j++) {
                    int zeroBlockCount = getZeroBlock(i, j);
                    if (map[i][j] == 0) continue;
                    tempMap[i][j] = Math.max(map[i][j] - zeroBlockCount, 0);
                }
            }
            if (Arrays.deepEquals(map, tempMap)){
                System.out.println(0);
                break;
            }
            map = tempMap;

            // 총 연결 개수가 2개일 때 반환
            int numIsland = 0;
            visited = new boolean[n+1][m+1];
            for(int i = 2; i<= n-1; i++){
                for(int j = 2; j <= m-1; j++){
                    if (!visited[i][j] && map[i][j] > 0){
                        bfs(i, j);
                        numIsland++;
                    }
                }
            }

            if (numIsland >= 2){
                System.out.println(days);
                break;
            }
            else{
                days++;
            }

        }

    }
}
