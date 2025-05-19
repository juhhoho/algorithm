package dijkstra;

import java.util.*;

public class boj1753 {

    static class Node{
        int index, cost;
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }

    static List<Node>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();

        graph = new ArrayList[v+1];
        for(int i = 1; i<= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            graph[src].add(new Node(dest, cost));
        }

        int[] distance = dijkstra(v, start);
        for(int i = 1; i <= v; i++){
            int dist = distance[i];
            if (dist == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist);

        }
    }

    public static int[] dijkstra(int v, int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
        pq.offer(new Node(start, 0));

        int[] distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if (distance[curNode.index] < curNode.cost) continue;

            for(Node nextNode: graph[curNode.index]){
                if (distance[curNode.index] + nextNode.cost < distance[nextNode.index]){
                    distance[nextNode.index] = distance[curNode.index] + nextNode.cost;
                    pq.offer(new Node(nextNode.index, distance[nextNode.index]));
                }
            }
        }
        return distance;

    }
}
