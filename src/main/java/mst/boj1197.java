package mst;

import java.util.*;

public class boj1197 {
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static int find(int node){
        if(node == parent[node]){
            return node;
        }
        else{
            return parent[node] = find(parent[node]);
        }
    }
    public static void union(int node1, int node2){
        node1 = find(node1);
        node2 = find(node2);
        if(node1 != node2){
            parent[node2] = node1;
        }
    }

    public static class Edge{
        public int s;
        public int e;
        public int w;
        public Edge(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    public static void mst(int n){
        int edgeUsed = 0;
        int totalWeight = 0;

        while(edgeUsed < n -1){
            Edge cur = pq.poll();
            if(find(parent[cur.s]) != find(parent[cur.e])){
                union(cur.s, cur.e);
                edgeUsed++;
                totalWeight += cur.w;
            }
        }
        System.out.println(totalWeight);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        pq = new PriorityQueue<>((edge1, edge2)->{
            if(edge1.w < edge2.w) return -1; else return 1;
        });
        for(int i = 0 ; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();
            pq.add(new Edge(s, e, w));
        }

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        mst(n);

    }
}
