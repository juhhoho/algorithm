package implementation;

import java.util.*;

public class boj2887 {

    static int[] parent;

    static class Edge{
        int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) parent[i] = i;

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            list.add(new int[]{i, x, y, z});
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)-> o1.cost - o2.cost);
        for(int dim = 0; dim < 3; dim++){
            int dimIdx = dim;
            list.sort((o1, o2)->{
                if (dimIdx == 0) return o1[1] - o2[1];
                else if (dimIdx == 1) return o1[2] - o2[2];
                else return o1[3] - o2[3];
            });

            for(int i = 1; i < list.size(); i++){
                int min = Math.min(Math.min(
                        (int)Math.abs(list.get(i-1)[1] - list.get(i)[1]),
                        (int)Math.abs(list.get(i-1)[2] - list.get(i)[2])),
                        (int)Math.abs(list.get(i-1)[3] - list.get(i)[3])
                );
                pq.offer(new Edge(list.get(i-1)[0], list.get(i)[0], min));
            }
        }

        int edgeUsed = 0;
        int totalWeight = 0;

        while(!pq.isEmpty() && edgeUsed < n-1){
            Edge cur = pq.poll();

            if (find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                edgeUsed++;
                totalWeight += cur.cost;
            }

        }
        System.out.println(totalWeight);

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
}
