package implementation;

import java.util.*;

public class boj5427 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t = 0 ; t < test; t++){
            int m = sc.nextInt();
            int n = sc.nextInt();

            char[][] map = new char[n][m];
            int[][] fireMap = new int[n][m];
            List<int[]> fireStartList = new ArrayList<>();

            int start_row = -1;
            int start_col = -1;

            for(int i = 0; i < n; i++){
                char[] charArray = sc.next().toCharArray();
                for(int j = 0 ; j < m; j++){
                    map[i][j] = charArray[j];
                    if (map[i][j] == '*') fireStartList.add(new int[]{i,j});
                    else if (map[i][j] == '@') {
                        start_row = i;
                        start_col = j;
                    }
                }
            }

            floodFire(n, m, map, fireMap, fireStartList);

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{start_row, start_col, 0});

            boolean[][] visited = new boolean[n][m];
            visited[start_row][start_col] = true;

            boolean clear = false;
            int min = Integer.MAX_VALUE;

            while(!queue.isEmpty()){
                int[] cur = queue.poll();

                if (cur[0] == 0 || cur[0] == n-1 || cur[1] == 0 || cur[1] == m-1) {
                    clear = true;
                    min = Math.min(min, cur[2]+1);
                }

                for(int i = 0; i < 4; i++){
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];

                    if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] == '.' && cur[2]+1 < fireMap[nr][nc]){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2]+1});
                    }
                }
            }
            if (!clear) System.out.println("IMPOSSIBLE");
            else System.out.println(min);

        }
    }


    public static void floodFire(int n, int m, char[][] map, int[][] fireMap, List<int[]> fireStartList){

        for(int[] row: fireMap) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for(int[] loc: fireStartList){
            fireMap[loc[0]][loc[1]] = 0;
            queue.offer(new int[]{loc[0], loc[1], 0});
            visited[loc[0]][loc[1]] = true;
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] ){
                    char v = map[nr][nc];
                    if (v == '.' || v == '@') {
                        fireMap[nr][nc] = cur[2]+1;
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2]+1});
                    }
                }
            }
        }
    }
}
