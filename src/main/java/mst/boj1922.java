package mst;

import java.util.*;

public class boj1922 {
    public static class Edge{
        int v1,v2,c;

        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
    public static int find(int node){
        if (node == parent[node]) return node;
        else return parent[node] = find(parent[node]);
    }
    public static void union(int node1, int node2){
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2){
            parent[root1] = root2;
        }
    }

    static int[] parent;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.c));
        for(int i = 0; i < m; i++){
            pq.offer(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int edgeUsedCnt = 0;
        int totalWeight = 0;

        while(edgeUsedCnt < n-1 && !pq.isEmpty()){
            Edge cur = pq.poll();
            if (find(cur.v1) != find(cur.v2)){
                union(cur.v1, cur.v2);
                edgeUsedCnt++;
                totalWeight+=cur.c;
            }
        }
        System.out.println(totalWeight);
    }
}
