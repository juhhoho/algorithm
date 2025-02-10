package dfs;

import java.util.*;

public class boj11123 {

    static int[][] map;
    static boolean[][] visited;

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    public static void dfs(int h, int w, int i, int j){
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            int cr = cur[0];
            int cc = cur[1];

            if(!visited[cr][cc]){
                visited[cr][cc] = true;

                for(int k = 0 ; k < 4; k++){
                    int nr = cr + dr[k];
                    int nc = cc + dc[k];

                    if(0 <= nr && nr < h && 0 <= nc && nc < w && !visited[nr][nc] && map[nr][nc] == 1){
                        stack.push(new int[]{nr, nc});
                    }
                }
            }
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int t = 0 ; t < test; t++){
            int h = sc.nextInt();
            int w = sc.nextInt();

            visited = new boolean[h][w];
            map = new int[h][w];

            for(int i = 0 ; i < h; i++){
                char[] chars = sc.next().toCharArray();
                for(int j = 0; j < w; j++){
                    if(chars[j] == '#'){
                        map[i][j] = 1;
                    }
                }
            }

            int cnt = 0;

            for(int i = 0 ; i < h; i++){
                for(int j = 0; j < w; j++){
                    if (!visited[i][j] && map[i][j] == 1 ){
                        dfs(h, w, i, j);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }
}
