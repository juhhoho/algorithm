package implementation;

import java.util.*;

public class boj7569 {
    static int m,n,h;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dh = {-1, 1, 0, 0, 0, 0};
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();

        map = new int[h][n][m];
        visited = new boolean[h][n][m];
        int unRiped = 0;

        // j, n, m, days
        Queue<int[]> queue = new LinkedList<>();

        for(int k = 0; k < h; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    int value = sc.nextInt();
                    map[k][i][j] = value;
                    if (value == 0) unRiped++;
                    else if (value == 1) {
                        queue.offer(new int[]{k, i, j, 0});
                        visited[k][i][j] = true;
                    }
                }
            }
        }

        if(unRiped == 0) {
            System.out.println(0);
            System.exit(0);
        }


        boolean clear = false;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 6; i++){
                int nh = cur[0] + dh[i];
                int nr = cur[1] + dr[i];
                int nc = cur[2] + dc[i];
                if (0 <= nh && nh < h &&
                        0 <= nr && nr < n &&
                        0 <= nc && nc < m &&
                        !visited[nh][nr][nc] &&
                        map[nh][nr][nc] == 0
                ){
                    visited[nh][nr][nc] = true;
                    unRiped--;
                    if(unRiped == 0) {
                        System.out.println(cur[3]+1);
                        clear = true;
                        break;
                    }
                    queue.offer(new int[]{nh, nr, nc, cur[3]+1});
                }
            }
        }

        if(!clear) System.out.println(-1);

    }

}
