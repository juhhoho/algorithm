package dijkstra;

import java.util.*;

public class boj5972 {

    static ArrayList<Node>[] adjs;
    static int[] distance;

    public static class Node{
        public int idx;
        public int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void dijkstra(int n){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2)->{
            if (n1.cost < n2.cost) return -1;
            else return 1;
        });
        pq.offer(new Node(1, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(distance[cur.idx] < cur.cost) continue;

            for(Node adj: adjs[cur.idx]){
                if (distance[cur.idx] + adj.cost < distance[adj.idx]){
                    distance[adj.idx] = distance[cur.idx] + adj.cost;
                    pq.offer(new Node(adj.idx, distance[adj.idx]));
                }
            }
        }
        System.out.println(distance[n]);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        adjs = new ArrayList[n+1];
        for(int i = 1 ; i <= n; i++){
            adjs[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();
            adjs[s].add(new Node(e, c));
            adjs[e].add(new Node(s, c));
        }

        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        dijkstra(n);


    }
}
