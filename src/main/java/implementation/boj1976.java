package implementation;

import java.util.*;

public class boj1976 {
    static ArrayList<Integer>[] graph;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (i < j){
                    if (map[i][j] == 1){
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }
        }



        int[] routes = new int[m];
        for(int i = 0; i < m; i++) {
            routes[i] = sc.nextInt()-1;
        }

        boolean yes = true;
        for(int i = 1; i < m; i++){
            int start = routes[i-1];
            int end = routes[i];
            if (!bfs(n, start, end)) {
                System.out.println("NO");
                yes = false;
                break;
            }
        }
        if(yes) System.out.println("YES");
    }

    public static boolean bfs(int n, int start, int end){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[n];
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            if (cur == end) return true;

            for(int next: graph[cur]){
                if (!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return false;
    }
}
/*
1 2 * 4 * * * 8 9
 */