package implementation;

import java.util.*;

public class boj2631 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static char[][] map;
    static int[][] fireMap;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new char[n][m];
        fireMap = new int[n][m];

        int[] userStarts = new int[2];
        List<int[]> fireStarts = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                if (charArray[j] == 'J') {
                    userStarts[0] = i;
                    userStarts[1] = j;
                }
                else if (charArray[j] == 'F') {
                    fireStarts.add(new int[]{i, j});
                }
                map[i][j] = charArray[j];
            }
        }

        propagationFire(n, m, fireStarts);

        boolean[][] visited = new boolean[n][m];
        visited[userStarts[0]][userStarts[1]] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{userStarts[0], userStarts[1], 0});

        int res = -1;
        boolean finFlag = false;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
                int nd = cur[2] + 1;
                if (nr < 0 || nr > n -1 || nc < 0 || nc > m -1){
                    res = nd;
                    finFlag = true;
                }
                else{
                    if (!visited[nr][nc] && map[nr][nc] == '.' && nd < fireMap[nr][nc]){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, nd});
                    }
                }
            }
            if (finFlag) break;
        }

        if (res == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(res);


    }
    public static void propagationFire(int n, int m, List<int[]> fireStarts){

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for(int[] row: fireMap){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int[] fireStart: fireStarts){
            int fr = fireStart[0];
            int fc = fireStart[1];

            fireMap[fr][fc] = 0;
            queue.offer(new int[]{fr, fc, 0});
            visited[fr][fc] = true;
        }


        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int k = 0; k < 4; k++){
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] != '#'){
                    visited[nr][nc] = true;
                    fireMap[nr][nc] = cur[2] + 1;
                    queue.offer(new int[]{nr, nc, cur[2]+1});
                }
            }
        }

    }
}





