package topology_sorting;

import java.util.*;

public class boj1516 {
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] cost;

    public static void topology_sorting(int n){

        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++){
            if (indegree[i] == 0){
                queue.offer(i);
                dp[i] = cost[i];
            }
        }


        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int adj: graph[cur]){
                indegree[adj]--;

                dp[adj] = Math.max(dp[adj], dp[cur] + cost[adj]);

                if (indegree[adj] == 0){
                    queue.offer(adj);
                }
            }
        }
        for(int i = 1; i <= n; i++)
            System.out.println(dp[i]);


    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        indegree = new int[n+1];
        cost = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++){
            cost[i] = sc.nextInt();
            while(true){
                int before = sc.nextInt();
                if (before == -1){
                    break;
                }
                graph[before].add(i);
                indegree[i]++;
            }
        }
        topology_sorting(n);
    }
}

/*
indegree
1       2       3       4       5
0       1       1       2       1
0       0       0       1       1
0       0       0       0       0

cost
1       2       3       4       5
10      10      4       4       3

dp
1       2       3       4       5
10      20      14      18     17

--------------------------------------
그럼 처음에 queue{1}이고 가쥬아

여기서 1뽑아
그리고 2,3,4다음이니까
dp변경 봐줘
그럼 queue에 {2,3} 넣어줘

2뽑아
2 다음 없어 나가이자시가
3뽑아
3다음 45이네
dp변경봐줘

그다음 큐 넣어

끝이야.

 */