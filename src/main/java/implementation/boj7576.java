package implementation;

import java.util.*;

public class boj7576 {
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        map = new int[n][m];
        List<int[]> list = new ArrayList<>();
        int cnt = n * m;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) list.add(new int[]{i, j});
                if (map[i][j] == -1) cnt--;
            }
        }

        System.out.println(flooding(n, m, list, cnt));
    }

    public static int flooding(int n, int m, List<int[]> list, int cnt){
        Queue<int[]> queue = new LinkedList<>();
        for(int[] arr: list){
            queue.offer(new int[]{arr[0], arr[1], 1});
            cnt--;
        }

        if (cnt == 0) return 0;


        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && map[nr][nc] == 0){
                    map[nr][nc] = 1;
                    cnt--;
                    queue.offer(new int[]{nr, nc, cur[2]+1});
                }
            }
            if (cnt == 0) return cur[2];
        }
        return -1;

    }

}
