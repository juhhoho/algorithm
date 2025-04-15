package dijkstra;

import java.util.*;

public class boj1238 {
    static ArrayList<Node>[] graph;
    static class Node{
        int index, weight;
        public Node(int i, int w){
            this.index = i;
            this.weight = w;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();

            graph[s].add(new Node(e, c));
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) max = Math.max(max, dijkstra(n, i, x));

        System.out.println(max);
    }

    public static int dijkstra(int n, int start, int end){
        int[] distanceSE = new int[n+1];
        int[] distanceES = new int[n+1];
        Arrays.fill(distanceSE, Integer.MAX_VALUE);
        Arrays.fill(distanceES, Integer.MAX_VALUE);
        distanceSE[start] = 0;
        distanceES[end] = 0;
        PriorityQueue<Node> pqSE = new PriorityQueue<>((o1, o2)->{
           return o1.weight - o2.weight;
        });
        PriorityQueue<Node> pqES = new PriorityQueue<>((o1, o2)->{
            return o1.weight - o2.weight;
        });
        pqSE.offer(new Node(start, 0));

        while(!pqSE.isEmpty()){
            Node curNode = pqSE.poll();

            if (curNode.weight < distanceSE[curNode.index]) continue;

            for(Node next: graph[curNode.index]){
                if (distanceSE[curNode.index] + next.weight < distanceSE[next.index]){
                    distanceSE[next.index] = distanceSE[curNode.index] + next.weight;
                    pqSE.offer(new Node(next.index, distanceSE[next.index]));
                }
            }
        }
        pqES.offer(new Node(end, 0));

        while(!pqES.isEmpty()){
            Node curNode = pqES.poll();

            if (curNode.weight < distanceES[curNode.index]) continue;

            for(Node next: graph[curNode.index]){
                if (distanceES[curNode.index] + next.weight < distanceES[next.index]){
                    distanceES[next.index] = distanceES[curNode.index] + next.weight;
                    pqES.offer(new Node(next.index, distanceES[next.index]));
                }
            }
        }
        return distanceSE[end] + distanceES[start];
    }
}
