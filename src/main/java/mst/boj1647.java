package mst;

import java.util.*;

public class boj1647 {
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static int find(int node){
        if (node == parent[node]){
            return node;
        }
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
        int m = sc.nextInt();

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(e->e.cost));
        for(int i = 0 ; i <m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();
            pq.add(new Edge(s,e,w));
        }

        List<Integer> costs = new ArrayList<>();
        int edgeUsed = 0;
        while (edgeUsed < n - 1 && !pq.isEmpty()) {
            Edge cur = pq.poll();
            if(find(parent[cur.s])!= find(parent[cur.e])){
                union(cur.s, cur.e);
                edgeUsed++;
                costs.add(cur.cost);
            }
        }

        costs.sort((i1, i2)->{if (i1 < i2) return -1; else return 1;});
        int res = 0;
        for(int i = 0 ; i < costs.size()-1; i++){
            res += costs.get(i);
        }
        System.out.println(res);

    }
}

/*
최초에 n개의 집은 모두 연결되어있음

n개의 집을 두개의 집합으로 분할 할 계획
(1) 각 집합에는 집이 최소 1개 있어야 하고,
(2) 각 집합은 최소의 에지만 써서 연결이 되야함
그 때 각 집합을 연결하는 에지의 최소값의 합
 */

