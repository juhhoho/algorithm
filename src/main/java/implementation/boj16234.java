package implementation;

import java.util.*;

public class boj16234 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int low = sc.nextInt();
        int high = sc.nextInt();

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        int cnt = 0;

        while(true){
            visited = new boolean[n][n];
            int bfsCallCnt = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j]){
                        ArrayList<int[]> list = bfs(n, i, j, low, high);
                        if (list.size() <= 2) continue;
                        bfsCallCnt++;

                        int average = list.get(list.size()-1)[0];
                        list.remove(list.size()-1);

                        for(int[] arr: list){
                            map[arr[0]][arr[1]] = average;
                        }
                    }
                }
            }

            if (bfsCallCnt == 0) break;
            cnt++;
        }
        System.out.println(cnt);
    }
    public static ArrayList<int[]> bfs(int n, int i, int j, int low, int high){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        ArrayList<int[]> res = new ArrayList<>();
        res.add(new int[]{i, j});

        int sum = map[i][j];

        visited[i][j] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                if (0 <= nr && nr < n && 0 <= nc && nc < n && !visited[nr][nc]){
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                    if (low <= diff && diff <= high){
                        visited[nr][nc] = true;
                        sum += map[nr][nc];
                        res.add(new int[]{nr, nc});
                        queue.offer(new int[]{nr, nc});
                    }

                }
            }
        }
        res.add(new int[]{(int)(sum/res.size()), 0});
        return res;
    }
}
