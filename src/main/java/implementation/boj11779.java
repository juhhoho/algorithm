package implementation;

import java.util.*;

public class boj11779 {
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

        for(int i = 0; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();
            graph[s].add(new Node(e, c));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        dijkstra(n, start, end);
    }
    public static void dijkstra(int n, int start, int end){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> o1.cost - o2.cost);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (distance[cur.index] < cur.cost) continue;

            for(Node next: graph[cur.index]){
                if (distance[cur.index] + next.cost < distance[next.index]){
                    distance[next.index] = distance[cur.index] + next.cost;
                    pq.offer(new Node(next.index, distance[next.index]));
                    parent[next.index] = cur.index;
                }
            }
        }
        List<Integer> path = restorePath(end, parent);

        System.out.println(distance[end]);
        System.out.println(path.size());
        for(int nodeIndex: path) System.out.print(nodeIndex + " ");
    }

    public static List<Integer> restorePath(int end, int[] parent){
        List<Integer> path = new ArrayList<>();
        int cur = end;
        while(cur != -1){
            path.add(cur);
            cur = parent[cur];
        }

        Collections.reverse(path);
        return path;
    }
}
