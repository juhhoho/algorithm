package mst;

import java.util.*;

public class boj1197 {
    public static class Edge {
        int start, end, cost;
        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static PriorityQueue<Edge> pq;
    public static int[] parent;

    public static int find(int node) {
        if (node == parent[node]) return node;
        return parent[node] = find(parent[node]); // 경로 압축 최적화
    }

    public static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent[root1] = root2;  // 부모를 최상위 루트로 설정
        }
    }

    public static void mst(int v) {
        int edgeCnt = 0;
        int totalWeight = 0;

        while (edgeCnt < v - 1 && !pq.isEmpty()) {  // 모든 간선이 사용되지 않을 가능성 고려
            Edge edge = pq.poll();

            if (find(edge.start) != find(edge.end)) { // 수정
                union(edge.start, edge.end);
                edgeCnt++;
                totalWeight += edge.cost;
            }
        }

        System.out.println(totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        for (int i = 0; i < e; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            pq.offer(new Edge(start, end, cost));
        }

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        mst(v);
    }
}
