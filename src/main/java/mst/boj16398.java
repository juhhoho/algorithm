package mst;

import java.util.*;

public class boj16398 {

    public static PriorityQueue<Edge> pq;
    public static int[] parent;

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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        pq = new PriorityQueue<>(Comparator.comparingInt(o->o.c));
        parent = new int[n+1];
        for(int i = 1 ; i <= n; i++){
            parent[i] = i;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1 ; j <= n; j++){
                int c = sc.nextInt();
                if (j > i){
                    pq.offer(new Edge(i, j, c));
                }
            }
        }

        int edgeUsedCnt = 0;
        int totalCost = 0;

        while(edgeUsedCnt < n-1 && !pq.isEmpty()){
            Edge cur = pq.poll();

            if (find(cur.v1) != find(cur.v2)){
                union(cur.v1, cur.v2);
                edgeUsedCnt++;
                totalCost+= cur.c;
            }
        }
        System.out.println(totalCost);
    }
}
