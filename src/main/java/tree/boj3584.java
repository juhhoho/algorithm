package tree;

import java.util.*;

public class boj3584 {

    public static ArrayList<Integer>[] tree;
    public static int[][] dp;
    public static int[] depth;
    public static int log;

    public static void bfs(int n, int root){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{root, 1}); // node, depth

        boolean[] visited = new boolean[n+1];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDepth = cur[1];

            if (!visited[curNode]){
                visited[curNode] = true;

                for(int next: tree[curNode]){
                    if (!visited[next]){
                        queue.offer(new int[]{next, curDepth + 1});
                        dp[next][0] = curNode;
                        depth[next] = curDepth + 1;
                    }
                }
            }
        }
    }

    public static void preprocess(int n, int root){
        log = (int) (Math.log(n)/Math.log(2)) + 1;
        dp = new int[n+1][log];
        depth = new int[n+1];
        depth[root] = 1;

        bfs(n, root);

        for(int i = 1; i < log; i++){
            for(int node = 1; node <= n; node++){
                if (dp[node][i-1] != 0){
                    dp[node][i] = dp[dp[node][i-1]][i-1];
                }
            }
        }
    }

    public static int getLCA(int n1, int n2){
        if (depth[n1] < depth[n2]){
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        for(int i = log-1; i >= 0; i--){
            if (depth[n1] - (1 << i) >= depth[n2]){
                n1 = dp[n1][i];
            }
        }

        if (n1 == n2) return n1;

        for(int i = log - 1; i >= 0 ; i--){
            if (dp[n1][i] != dp[n2][i]){
                n1 = dp[n1][i];
                n2 = dp[n2][i];
            }
        }
        return dp[n1][0];
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();
        for(int t = 0; t < test; t++){
            int n = sc.nextInt();

            tree = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                tree[i] = new ArrayList<>();
            }

            boolean[] hasParent = new boolean[n+1];

            for(int i =0; i < n-1; i++){
                int parent = sc.nextInt();
                int child = sc.nextInt();
                tree[parent].add(child);
                hasParent[child] = true;
            }

            int root = -1;
            for (int i = 1; i <= n; i++){
                if (!hasParent[i]) {
                    root = i;
                    break;
                }
            }

            preprocess(n, root); // 루트 노드 기반으로 LCA 전처리

            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            System.out.println(getLCA(n1, n2));

        }
    }
}
