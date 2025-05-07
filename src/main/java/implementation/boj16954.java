package implementation;

import java.util.*;

public class boj16954 {

    static char[][] map = new char[8][8];
    // 12시 방향에서 반시계
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1, 0};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 8; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 0; j < 8; j++){
                map[i][j] = charArray[j];
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{7, 0});
        boolean fin = false;

        while(!queue.isEmpty() && !fin){

            boolean[][] visited = new boolean[8][8];

            for(int i = 0; i < queue.size(); i++){
                int[] cur = queue.poll();

                if (map[cur[0]][cur[1]] == '#') continue;

                if (cur[0] == 0 && cur[1] == 7) fin = true;

                for(int j = 0; j < 9; j++){
                    int nr = cur[0] + dr[j];
                    int nc = cur[1] + dc[j];
                    if (0 <= nr && nr < 8 && 0 <= nc && nc < 8 && !visited[nr][nc] && map[nr][nc] == '.'){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            changeMap();
        }

        if (!fin) System.out.println(0);
        else System.out.println(1);

    }
    public static void changeMap(){
        for(int i = 7; i > 0; i--){
            map[i] = map[i-1].clone();
        }
        map[0] = new char[]{'.','.','.','.','.','.','.','.'};


    }
}
