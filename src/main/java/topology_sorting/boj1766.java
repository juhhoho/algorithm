package topology_sorting;

import java.util.*;

public class boj1766 {
    static ArrayList<Integer>[] graph;
    static int[] counts;

    public static void topology_sorting(int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=n; i++){
            if(counts[i] == 0){
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()){
            int cur = pq.poll();
            System.out.print(cur + " ");
            for(int adj: graph[cur]){
                counts[adj]--;
                if(counts[adj] == 0)
                    pq.offer(adj);
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        counts = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        int m = sc.nextInt();
        for(int i = 0 ; i < m; i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            graph[first].add(second);
            counts[second]++;
        }
        topology_sorting(n);
    }
}
/*
1
2
3: 1
4: 2

[1 ,1 ,0, 0]

4
4와 인접한 2에서 1빼줌
> [1, 0, 0, 0]

그러면 2가 0이 되었네


 */