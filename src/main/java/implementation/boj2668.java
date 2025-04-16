package implementation;

import java.util.*;

public class boj2668 {
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph[i].add(sc.nextInt());
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            if (bfs(i, n)) set.add(i);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        set.forEach((o)-> {
            pq.offer(o);
        });
        System.out.println(set.size());
        while(!pq.isEmpty()) System.out.println(pq.poll());
    }
    public static boolean bfs(int start, int n){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});

        boolean[] visited = new boolean[n+1];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0] == start && cur[1] > 0) return true;

            for(int next: graph[cur[0]]){
                if (!visited[next]){
                    visited[next] = true;
                    queue.offer(new int[]{next, cur[1]+1});
                }
            }
        }
        return false;
    }
}
/*
가로 관점: 1에서 1, 3가능
세로 관점: 전체에서 123 가능
일치하는건 1, 3

가로관점: 2에서 1,3 가능
세로관점: 아무것도 2로 못감


2는 1,

 */