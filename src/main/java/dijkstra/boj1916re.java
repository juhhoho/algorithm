package dijkstra;

import java.util.*;

public class boj1916re {

    public static ArrayList<Node>[] graph;
    public static int[] distance;

    public static class Node{
        int index, cost;
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static void dijkstra(int n, int srcNode, int destNode){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.cost));
        pq.offer(new Node(srcNode, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (distance[cur.index] < cur.cost) continue;

            for(Node next : graph[cur.index]){
                if (distance[cur.index] + next.cost < distance[next.index]){
                    distance[next.index] = distance[cur.index] + next.cost;
                    pq.offer(new Node(next.index, distance[next.index]));
                }
            }
        }
        System.out.println(Arrays.toString(distance));
        // return distance[destNode];
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();

            graph[src].add(new Node(dest, cost));
        }

        int srcNode = sc.nextInt();
        int destNode = sc.nextInt();

        distance = new int[n+1];
        for(int i = 1; i <= n; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[srcNode] = 0;

        dijkstra(n, srcNode, destNode);

    }
}
