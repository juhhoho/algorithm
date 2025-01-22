package mst;

import java.util.*;

public class boj1368 {
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static int find(int node){
        if (parent[node] == node)
            return node;
        else{
            return parent[node] = find(parent[node]);
        }
    }

    public static void union(int node1, int node2){
        node1 = find(node1);
        node2 = find(node2);
        if(parent[node1] != parent[node2]){
            parent[node2] = node1;
        }
    }

    public static class Edge{
        public int s;
        public int e;
        public int cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        pq = new PriorityQueue<>((e1, e2)->{if(e1.cost < e2.cost) return -1; else return 1;});

        int min = Integer.MAX_VALUE;
        int min_idx = -1;
        List<int[]> temp = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            int cost = sc.nextInt();
            temp.add(new int[]{i+1, cost});
            if (cost < min){
                min = cost;
                min_idx = i + 1;
            }
        }
        for(int i = 0; i < n; i++){
            if (min_idx != i+1){
                pq.add(new Edge(0, i+1 ,temp.get(i)[1]));
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                int cost = sc.nextInt();
                if(i < j)
                    pq.add(new Edge(i, j, cost));
            }
        }

        parent = new int[n+1];
        for(int i = 0 ; i <= n; i++){
            parent[i] = i;
        }
        parent[min_idx] = 0;

        int edgeUsed = 1;
        int total = min;

        while(edgeUsed < n && !pq.isEmpty()){
            Edge cur = pq.poll();
            if(find(parent[cur.s]) != find(parent[cur.e])){
                union(cur.s, cur.e);
                edgeUsed++;
                total += cur.cost;
            }
        }
        System.out.println(total);
    }
}
