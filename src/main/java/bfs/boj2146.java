package bfs;

import java.util.*;

public class boj2146 {

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<List<int[]>> graph = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        map = new int[n][n];
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        boolean[][] visited = new boolean[n][n];
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    graph.add(bfs(visited, n, i, j));
                }
            }
        }

        int minDist = Integer.MAX_VALUE;

        for(int i = 0; i < graph.size(); i++){
            List<int[]> home = graph.get(i);
            for(int j = i+1; j < graph.size(); j++){
                if (i == j) continue;
                List<int[]> away = graph.get(j);
                minDist = Math.min(minDist, getMinDist(home, away));
            }
        }
        System.out.println(minDist-1);
    }

    public static int getMinDist(List<int[]> home, List<int[]> away){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < home.size(); i++){
            for(int j = 0; j < away.size(); j++){
                int diffRow = Math.abs(home.get(i)[0] - away.get(j)[0]);
                int diffCol = Math.abs(home.get(i)[1] - away.get(j)[1]);
                min = Math.min(min, diffRow + diffCol);
            }
        }
        return min;
    }
    public static List<int[]> bfs(boolean[][] visited, int n, int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        visited[row][col] = true;

        List<int[]> list = new ArrayList<>();

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            list.add(cur);

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < n && !visited[nr][nc] && map[nr][nc] == 1){
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        Iterator<int[]> iter = list.iterator();
        while(iter.hasNext()){
            int[] cur = iter.next();
            if (!isNearbyBeach(n, cur[0], cur[1])) iter.remove();
        }

        return list;

    }
    public static boolean isNearbyBeach(int n, int row, int col){
        for(int i = 0; i < 4; i++){
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < n && map[nr][nc] == 0) return true;
        }
        return false;
    }
}
