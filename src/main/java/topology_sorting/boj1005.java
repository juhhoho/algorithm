package topology_sorting;

import java.util.*;

public class boj1005 {
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] cost;

    public static void topology_sorting(int n, int target){
        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                queue.offer(i);
                dp[i] = cost[i];
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int adj : graph[cur]){
                indegree[adj]--;
                dp[adj] = Math.max(dp[adj], dp[cur] + cost[adj]);

                if(indegree[adj] == 0){
                    queue.offer(adj);
                }
            }
        }
        System.out.println(dp[target]);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int t = 0; t < test; t++){
            int n = sc.nextInt();
            indegree = new int[n+1];
            cost = new int[n+1];
            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            int m = sc.nextInt();
            for(int i = 1; i <= n; i++){
                cost[i] = sc.nextInt();
            }
            for(int i = 0 ; i < m; i++){
                int before = sc.nextInt();
                int after = sc.nextInt();
                graph[before].add(after);
                indegree[after]++;
            }

            int target = sc.nextInt();

            topology_sorting(n, target);
        }
    }

}
