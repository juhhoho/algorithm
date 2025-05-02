package implementation;

import java.util.*;

public class boj2638 {

    static int n, m;
    static int[][] map;
    static int[][] cnt;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        int cheese = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m ; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) cheese++;
            }
        }

        int days = 0;
        while(cheese > 0){
            cnt = new int[n][m];
            cheese -= getRemovedCheese();
            days++;
        }
        System.out.println(days);
    }
    public static int getRemovedCheese(){

        Set<List<Integer>> set = new HashSet<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc]){
                    if (map[nr][nc] == 0){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                    else if (map[nr][nc] == 1){
                        if (cnt[nr][nc] == 0) cnt[nr][nc]++;
                        else set.add(Arrays.asList(nr, nc));
                    }
                }
            }
        }

        set.forEach((k)->{
            map[k.get(0)][k.get(1)] = 0;
        });

        return set.size();
    }
}
