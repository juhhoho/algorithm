package implementation;

import java.util.*;

public class boj2636 {
    static int n,m;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        int cheeseCnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) cheeseCnt++;
            }
        }

        int days = 0;
        List<Integer> extra = new ArrayList<>();
        while(cheeseCnt > 0){
            List<int[]> removedList = getNearbyAir();
            extra.add(removedList.size());
            cheeseCnt -= removedList.size();
            for(int[] arr: removedList){
                map[arr[0]][arr[1]] = 0;
            }
            days++;
        }
        System.out.println(days);
        System.out.println(extra.get(extra.size()-1));

    }

    public static List<int[]> getNearbyAir(){
        List<int[]> list = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] == 0){
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
                if (0 <= nr && nr < n && 0 <= nc && nc < m && map[nr][nc] == 1){
                    set.add(Arrays.asList(nr, nc));
                }
            }
        }

        set.forEach((value)->{
            list.add(new int[]{value.get(0), value.get(1)});
        });


        return list;
    }
}
