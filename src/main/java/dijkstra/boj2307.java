package dijkstra;

import java.util.*;

public class boj2307 {
    static List<Node>[] graph;
    static class Node{
        int index, cost;
        public Node(int i, int c){
            this.index = i;
            this.cost = c;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        List<int[]> vertexList = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int cost = sc.nextInt();

            vertexList.add(new int[]{v1,v2});

            graph[v1].add(new Node(v2, cost));
            graph[v2].add(new Node(v1, cost));
        }


        int max = Integer.MIN_VALUE;
        int originalCost = dijkstra(n);
        System.out.println("originalCost = " + originalCost);
        for(int[] vertex: vertexList){
            int minCost = limitDijkstra(n, vertex);
            System.out.println("minCost = " + minCost);
            if (minCost != -1) max = Math.max(max, minCost - originalCost);
        }
        if (max != Integer.MIN_VALUE) System.out.println(max);
        else System.out.println(-1);
    }

    public static int dijkstra(int n){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{return o1.cost - o2.cost;});
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (distance[cur.index] < cur.cost) continue;

            for(Node next: graph[cur.index]){
                if (distance[cur.index] + next.cost < distance[next.index]){
                    distance[next.index] = distance[cur.index] + next.cost;
                    pq.offer(new Node(next.index, distance[next.index]));
                }
            }
        }
        return distance[n];
    }

    public static int limitDijkstra(int n, int[] vertex){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{return o1.cost - o2.cost;});
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (distance[cur.index] < cur.cost) continue;

            for(Node next: graph[cur.index]){
                if ((cur.index == vertex[0] && next.index == vertex[1]) || (cur.index == vertex[1] && next.index == vertex[0])) continue;
                if (distance[cur.index] + next.cost < distance[next.index]){
                    distance[next.index] = distance[cur.index] + next.cost;
                    pq.offer(new Node(next.index, distance[next.index]));
                }
            }
        }
        return distance[n] != Integer.MAX_VALUE ? distance[n] : -1;
    }
}
