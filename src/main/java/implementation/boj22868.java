package implementation;

import java.util.*;

public class boj22868 {
    static List<Integer>[] graph;
    static List<Integer> used = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        int res = bfs(n, start, end);
        res += bfs(n, end, start);

        System.out.println(res);
    }

    public static int bfs(int n, int start, int end){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});

        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        int dist = 0;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if (cur[0] == end) {
                dist = cur[1];
                break;
            }

            for(int next: graph[cur[0]]){
                if(!visited[next] && !used.contains(next)){
                    queue.offer(new int[]{next, cur[1]+1});
                    visited[next] = true;
                    parent[next] = cur[0];
                }
            }
        }

        if (used.isEmpty()) removePath(parent, start, end);
        return dist;
    }

    public static void removePath(int[] parent, int start, int end){

        int cur = end;
        while(cur != -1){
            used.add(cur);
            cur = parent[cur];
        }

        used.remove(used.size()-1);
        used.remove(0);
    }
}

