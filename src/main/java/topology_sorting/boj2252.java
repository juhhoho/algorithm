package topology_sorting;

import java.util.*;

public class boj2252 {

    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        indegree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m; i++){
            int before = sc.nextInt();
            int after = sc.nextInt();

            // after 학생이 나오려면 앞에 몇명이 있어야 하는지
            indegree[after]++;

            // before 학생은 after 학생들의 집합 앞에 위치
            graph[before].add(after);

        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++){
            if (indegree[i] == 0){
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            System.out.print(cur + " ");

            for(int adj : graph[cur]){
                indegree[adj]--;
                if(indegree[adj] == 0){
                    pq.offer(adj);
                }
            }
        }

    }
}
