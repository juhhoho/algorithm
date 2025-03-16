package bfs;

import java.util.*;

public class boj2660 {
    
    public static ArrayList<Integer>[] graph;
    
    public static class Node{
        int loc;
        int dist;

        public Node(int loc, int dist) {
            this.loc = loc;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        while(true){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            if (v1 == -1 && v2 == -1) break;

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        HashMap<Integer, Integer> hash = new HashMap<>();

        int res = Integer.MAX_VALUE;
        
        for(int i = 1; i<= n; i++){
            int minDist = 0;
            Queue<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[n+1];
            queue.offer(new Node(i, 0));
            
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                if(!visited[cur.loc]){
                    visited[cur.loc] = true;
                    minDist = cur.dist;
                    
                    for(int adj : graph[cur.loc]){
                        if (!visited[adj]){
                            queue.offer(new Node(adj, cur.dist + 1));
                        }
                    }
                }
            }
            res = Math.min(minDist, res);
            hash.put(i, minDist);
        }

        ArrayList<Integer> candidateList = new ArrayList<>();

        for(int i = 1 ; i <= hash.size(); i++){
            if (hash.get(i) == res){
                candidateList.add(i);
            }
        }

        System.out.printf("%d %d\n", res, candidateList.size());
        for(int member: candidateList)
            System.out.print(member + " ");

    }
}