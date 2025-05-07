package implementation;

import java.util.*;

public class boj1197 {

    static class Edge{
        int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            pq.offer(new Edge(from, to, cost));
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        kruskalMST(n);
    }
    public static void kruskalMST(int n){
        int edgeUsed = 0;
        int totalWeight = 0;

        while(edgeUsed < n -1 && !pq.isEmpty()){
            Edge cur = pq.poll();

            if (find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                totalWeight += cur.cost;
                edgeUsed++;
            }
        }
        System.out.println(totalWeight);
    }

    public static int find(int node){
        if (node == parent[node]) return node;
        return parent[node] = find(parent[node]);
    }
    public static void union(int node1, int node2){
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2){
            parent[root1] = root2;
        }
    }
}
