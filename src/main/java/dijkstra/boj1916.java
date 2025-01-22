package dijkstra;

import java.util.*;

public class boj1916 {
    static ArrayList<Adjacent>[] adjs;
    static int[] distance;

    public static class Adjacent{
        public int idx;
        public int cost;

        public Adjacent(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start, int end){
        PriorityQueue<Adjacent> pq = new PriorityQueue<>((node1, node2)->{
            if(node1.cost < node2.cost) return -1;
            else return 1;
        }) ;

        pq.offer(new Adjacent(start, 0));

        while(!pq.isEmpty()){
            Adjacent cur = pq.poll();

            if(distance[cur.idx] < cur.cost)
                continue;

            for(Adjacent adj: adjs[cur.idx]){
                if (distance[cur.idx] + adj.cost < distance[adj.idx]){
                    distance[adj.idx] = distance[cur.idx] + adj.cost;
                    pq.offer(new Adjacent(adj.idx, distance[adj.idx]));
                }
            }
        }
        System.out.println(Arrays.toString(distance));
        System.out.println(distance[end]);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        adjs = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            adjs[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m; i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int c = sc.nextInt();
            adjs[src].add(new Adjacent(dest, c));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        distance = new int[n+1];
        for(int i = 1; i <= n;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;

        dijkstra(start, end);

    }
}
