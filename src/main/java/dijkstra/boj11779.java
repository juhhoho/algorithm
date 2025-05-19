package dijkstra;

import java.util.*;

public class boj11779 {

    static List<Node>[] graph;
    static class Node{
        int index, cost;
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 도시 개수
        int m = sc.nextInt(); // 버스 개수

        graph = new ArrayList[n+1];
        for(int i = 1; i<= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            graph[src].add(new Node(dest, cost));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        dijkstra(n, start, end);

    }
    public static void dijkstra(int n , int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->o1.cost-o2.cost);
        pq.offer(new Node(start, 0));

        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if (distance[curNode.index] < curNode.cost) continue;

            for(Node nextNode: graph[curNode.index]){
                if (distance[curNode.index] + nextNode.cost < distance[nextNode.index]){
                    distance[nextNode.index] = distance[curNode.index] + nextNode.cost;
                    pq.offer(new Node(nextNode.index, distance[nextNode.index]));
                    parent[nextNode.index] = curNode.index;
                }
            }
        }

        List<Integer> path = restorePath(end, parent);

        System.out.println(distance[end]);
        System.out.println(path.size());

        for(int node: path)
            System.out.print(node + " ");
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
