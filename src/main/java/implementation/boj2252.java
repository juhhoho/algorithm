package implementation;

import java.util.*;

public class boj2252 {
    static List<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        indegree = new int[n+1];

        for(int i = 0; i < m; i++){
            int front = sc.nextInt();
            int back = sc.nextInt();
            graph[front].add(back);
            indegree[back]++;
        }
        topology_sorting(n);
    }

    public static void topology_sorting(int n){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            if (indegree[i] == 0) pq.offer(i);
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();

            System.out.print(cur + " ");

            for(int next: graph[cur]){
                indegree[next]--;
                if (indegree[next] == 0) pq.offer(next);
            }
        }
    }
}
