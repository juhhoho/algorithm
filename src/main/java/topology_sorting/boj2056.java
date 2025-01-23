package topology_sorting;

import java.util.*;

public class boj2056 {
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] times;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        graph = new ArrayList[n + 1];
        indegree = new int[n + 1];
        times = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++){
            int after = i;
            times[i] = sc.nextInt();
            int cnt_before = sc.nextInt();
            for(int j = 0 ; j < cnt_before; j++){
                int before = sc.nextInt();
                graph[before].add(after);
                indegree[after]++;
            }
        }

        int[] dp = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                dp[i] = times[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                indegree[next]--;

                dp[next] = Math.max(dp[next], dp[cur] + times[next]);

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        int max = 0;
        for (int value : dp) {
            max = Math.max(max, value);
        }
        System.out.println(max);

    }
}

