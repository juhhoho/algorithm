package dijkstra;

import java.util.*;

public class boj1504 {
    static class Node{
        int index, cost;
        public Node(int i, int c){
            this.index = i;
            this.cost = c;
        }
    }
    static List<Node>[] graph;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 0 ; i < m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int cost = sc.nextInt();
            graph[v1].add(new Node(v2, cost));
            graph[v2].add(new Node(v1, cost));
        }

        int t1 = sc.nextInt();
        int t2 = sc.nextInt();

        int s2t1 = dijkstra(n, 1, t1);
        int t12t2 = dijkstra(n, t1, t2);
        int t22e = dijkstra(n, t2, n);
        int sum1 = s2t1 + t12t2 + t22e;
        boolean canOne = true;
        if (s2t1 == -1 || t12t2 == -1 || t22e == -1) canOne = false;

        int s2t2 = dijkstra(n, 1, t2);
        int t22t1 = dijkstra(n, t2, t1);
        int t12e = dijkstra(n, t1, n);
        int sum2 = s2t2 + t22t1 + t12e;
        boolean canTwo = true;
        if (s2t2 == -1 || t22t1 == -1 || t12e == -1) canTwo = false;

        if (canOne && !canTwo) System.out.println(sum1);
        else if (!canOne && canTwo) System.out.println(sum2);
        else if (!canOne && !canTwo) System.out.println(-1);
        else System.out.println(Math.min(sum1, sum2));


    }
    public static int dijkstra(int n, int start, int end){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{return o1.cost-o2.cost;});
        pq.offer(new Node(start, 0));

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
        if (distance[end] == Integer.MAX_VALUE || distance[end] < 0) return -1;
        return distance[end];
    }
}
