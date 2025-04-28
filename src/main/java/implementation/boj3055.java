package implementation;

import java.util.*;

public class boj3055 {
    static int[][] map;
    static int[][] floodMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n][m];
        floodMap = new int[n][m];

        List<int[]> floodStart = new ArrayList<>();
        int start_row = -1, start_col = -1;
        int end_row = -1, end_col = -1;
        for(int i = 0; i < n; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                int value = charArray[j];
                map[i][j] = value;
                if (value == 'D') {
                    end_row = i;
                    end_col = j;
                }
                else if (value == 'S'){
                    start_row = i;
                    start_col = j;
                }
                else if (value == '*'){
                    floodStart.add(new int[]{i, j});
                }
            }
        }

        getFloodMap(n, m, floodStart, end_row, end_col);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_row, start_col, 0});

        boolean[][] visited = new boolean[n][m];
        visited[start_row][start_col] = true;


        boolean clear = false;
        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();


            if (cur[0] == end_row && cur[1] == end_col) {
                min = Math.min(min, cur[2]);
                clear = true;
            }

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && (map[nr][nc] == '.' || map[nr][nc] == 'D') && cur[2] + 1 < floodMap[nr][nc]){
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, cur[2]+1});
                }
            }
        }
        if (!clear) System.out.println("KAKTUS");
        else System.out.println(min);
    }

    public static void getFloodMap(int n, int m, List<int[]> floodStart, int end_row, int end_col){

        for(int[] row: floodMap){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for(int[] loc: floodStart){
            floodMap[loc[0]][loc[1]] = 0;
            queue.offer(new int[]{loc[0], loc[1], 0});
            visited[loc[0]][loc[1]] = true;
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc]){
                    if (map[nr][nc] != 'D' && map[nr][nc] != 'X'){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2]+1});
                        floodMap[nr][nc] = cur[2] + 1;
                    }
                }
            }
        }
    }
}
