package topology_sorting;

import java.util.*;

public class boj1766 {

    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void topology_sorting(int n){

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->{
            if (o1 < o2) return -1;
            else return 1;
        });
        for(int i = 1 ; i <= n; i++){
            if (indegree[i] == 0){
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            System.out.print(cur + " ");

            for(int element : graph[cur]){
                indegree[element]--;
                if (indegree[element] == 0){
                    pq.offer(element);
                }
            }
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        indegree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= m; i++){
            int before = sc.nextInt();
            int after = sc.nextInt();

            indegree[after]++;

            graph[before].add(after);
        }

        topology_sorting(n);


    }

}
