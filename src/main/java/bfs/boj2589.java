package bfs;

import java.util.*;

public class boj2589 {

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[][] map;

    public static class Node{
        int cr, cc, dist;

        public Node(int cr, int cc, int dist) {
            this.cr = cr;
            this.cc = cc;
            this.dist = dist;
        }
    }

    public static int getMaxLength(int n, int m, int cr, int cc){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(cr, cc, 0));

        boolean[][] visited = new boolean[n+1][m+1];

        int maxDist = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(!visited[cur.cr][cur.cc]){
                visited[cur.cr][cur.cc] = true;
                maxDist = cur.dist;

                for(int k = 0; k < 4; k++){
                    int nr = cur.cr + dr[k];
                    int nc = cur.cc + dc[k];

                    if (1 <= nr && nr <= n && 1 <= nc && nc <= m && !visited[nr][nc] && map[nr][nc] == 1){
                        queue.offer(new Node(nr, nc, cur.dist + 1));
                    }
                }
            }
        }
        return maxDist;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 1; j <= m; j++){
                map[i][j] = (charArray[j - 1] == 'L') ? 1 : 0;
            }
        }

        int max = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(map[i][j] == 1){
                    max = Math.max(getMaxLength(n, m, i, j), max);
                }
            }
        }

        System.out.println(max);

    }
}
