package bfs;

import java.util.*;

public class boj1240 {

    public static ArrayList<Edge>[] graph;

    public static class Edge {
        int dest, cost;
        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int getLength(int n, int src, int dest) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(new int[]{src, 0}); // (현재 노드, 누적 거리)
        visited[src] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int dist = cur[1];

            if (node == dest) return dist; // 목적지 도착 시 거리 반환

            for (Edge edge : graph[node]) {
                if (!visited[edge.dest]) {
                    visited[edge.dest] = true;
                    queue.offer(new int[]{edge.dest, dist + edge.cost});
                }
            }
        }
        return -1; // 도달할 수 없는 경우 (문제 조건상 존재하지 않음)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            graph[src].add(new Edge(dest, cost));
            graph[dest].add(new Edge(src, cost));
        }

        for (int i = 0; i < m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            System.out.println(getLength(n, src, dest));
        }
    }
}
