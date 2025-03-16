package bfs;

import java.util.*;

public class boj2606 {

    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        int virused = -1;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if (!visited[cur]){
                visited[cur] = true;
                virused++;

                for(int adj : graph[cur]){
                    if (!visited[adj]){
                        queue.offer(adj);
                    }
                }
            }
        }
        System.out.println(virused);
    }
}
