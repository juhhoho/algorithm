package implementation;

import java.util.*;

public class boj9370 {
    static class Node{
        int index, cost;
        public Node(int i, int c){
            this.index = i;
            this.cost = c;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int test_case = 0; test_case < test; test_case++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int t = sc.nextInt();

            int s = sc.nextInt(); // 출발
            int g = sc.nextInt(); // 교차로 1
            int h = sc.nextInt(); //

            List<Node>[] graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for(int i = 0; i < m; i++){
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int cost = sc.nextInt();
                graph[v1].add(new Node(v2, cost));
                graph[v2].add(new Node(v1, cost));
            }
            List<Integer> candidates = new ArrayList<>();
            for(int i = 0; i < t; i++) candidates.add(sc.nextInt());

            List<Integer> list = new ArrayList<>();

            for(int candidate: candidates) {
                // 1. s -> candidate
                int cost1 = dijkstra(n, s, candidate, graph);
                if (cost1 == -1) continue;
                // 2. s -> v1 -> v2 -> candidate
                int cost2 = dijkstra(n, s, g, graph);
                int cost3 = dijkstra(n, h, candidate, graph);
                // 3. s -> v2 -> v1 -> candidate
                int cost4 = dijkstra(n, s, h, graph);
                int cost5 = dijkstra(n, g, candidate, graph);

                int bridge = -1;
                for(Node node: graph[g]){
                    if (node.index == h) bridge = node.cost;
                }
                if (bridge == -1) continue;

                cost1 -= bridge;

                if (cost2 != -1 && cost3 != -1 && cost1 == cost2 + cost3) list.add(candidate);
                else if (cost4 != -1 && cost5 != -1 && cost1 == cost4 + cost5 && !list.contains(candidate)) list.add(candidate);

            }
            Collections.sort(list);
            for(int v : list)
                System.out.print(v + " ");
            System.out.println();
        }
    }
    public static int dijkstra(int n, int start, int end, List<Node>[] graph){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
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

        if (distance[end] == Integer.MAX_VALUE) return -1;
        else return distance[end];
    }
}

//    public static List<Integer> dijkstra(int n, int start, int candidate, List<Node>[] graph){
//        int[] parent = new int[n+1];
//        int[] distance = new int[n+1];
//        Arrays.fill(distance, Integer.MAX_VALUE);
//        distance[start] = 0;
//
//        Arrays.fill(parent, -1);
//
//        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> o1.cost - o2.cost);
//        pq.offer(new Node(start, 0));
//
//        while(!pq.isEmpty()){
//            Node curNode = pq.poll();
//
//            if (distance[curNode.index] < curNode.cost) continue;
//
//            for(Node nextNode: graph[curNode.index]){
//                if (distance[curNode.index] + nextNode.cost < distance[nextNode.index]){
//                    distance[nextNode.index] = distance[curNode.index] + nextNode.cost;
//                    parent[nextNode.index] = curNode.index;
//                    pq.offer(new Node(nextNode.index, distance[nextNode.index]));
//                }
//            }
//        }
//        if (distance[candidate] != Integer.MAX_VALUE) return restorePath(candidate, parent);
//        else return new ArrayList<>();
//    }
//
//    public static List<Integer> restorePath(int candidate, int[] parent){
//        List<Integer> path = new ArrayList<>();
//        int cur = candidate;
//        while(cur != -1){
//            path.add(cur);
//            cur = parent[cur];
//        }
//        Collections.reverse(path);
//        return path;
//    }

