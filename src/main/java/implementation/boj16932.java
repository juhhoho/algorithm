package implementation;

import java.util.*;

public class boj16932 {

    static int[][] map;
    static boolean[][] visited;
    static List<Node> graph = new ArrayList<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node{
        Set<List<Integer>> adjNodes;
        int size;

        public Node(Set<List<Integer>> adjNodes, int size){
            this.adjNodes = adjNodes;
            this.size = size;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (!visited[i][j] && map[i][j] == 1){
                    getAdjNode(n, m, i, j);
                }
            }
        }

        int[][] cntMap = new int[n][m];
        for(int[] row: cntMap) Arrays.fill(row, 1);

        for(Node node: graph){
            for(List<Integer> loc: node.adjNodes){
                cntMap[loc.get(0)][loc.get(1)] += node.size;
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                max = Math.max(max, cntMap[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void getAdjNode(int n, int m, int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        visited[row][col] = true;

        int size = 0;
        Set<List<Integer>> set = new HashSet<>();

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            size++;

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m){
                    if (!visited[nr][nc] && map[nr][nc] == 1){
                        queue.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                    else if (map[nr][nc] == 0){
                        set.add(List.of(nr, nc));
                    }
                }
            }
        }

        graph.add(new Node(set, size));
    }
}