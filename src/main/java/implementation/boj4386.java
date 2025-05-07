package implementation;

import java.util.*;

public class boj4386 {

    static int[] parent;
    static class Edge{
        int from, to;
        double cost;
        public Edge(int f, int t, double c){
            this.from = f;
            this.to = t;
            this.cost = c;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) parent[i] = i;

        List<double[]> list = new ArrayList<>();

        for(int i = 0; i < n; i++){
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            list.add(new double[]{x, y});
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)-> Double.compare(o1.cost, o2.cost));

        for(int i = 0; i < n; i++){
            double[] fromLoc = list.get(i);
            for(int j = i+1; j < n; j++){
                double[] toLoc = list.get(j);

                double dx = toLoc[0] - fromLoc[0];
                double dy = toLoc[1] - fromLoc[1];

                double distance = Math.sqrt(dx*dx +  dy*dy);

                pq.offer(new Edge(i, j, distance));
            }
        }

        int edgeUsed = 0;
        double totalWeight = 0;

        while(!pq.isEmpty() && edgeUsed < n - 1){
            Edge cur = pq.poll();

            if (find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                edgeUsed++;
                totalWeight += cur.cost;
            }
        }
        System.out.printf("%.2f", totalWeight);
    }

    public static int find(int node){
        if (node == parent[node]) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int node1, int node2){
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }
}
