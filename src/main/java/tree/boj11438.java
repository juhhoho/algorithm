package tree;

import java.io.*;
import java.util.*;

public class boj11438 {

    public static ArrayList<Integer>[] tree;
    public static int log;
    public static int[][] dp;
    public static int[] depth;

    public static void bfs(int n, int root){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{root, 1});

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
        log = (int) (Math.log(n)/ Math.log(2)) + 1;
        dp = new int[n+1][log];
        depth = new int[n+1];
        depth[root] = 1;

        bfs(n, root);

        for(int i = 1; i < log; i++){
            for(int node = 1; node <= n; node++){
                if (dp[node][i-1] > 0){
                    dp[node][i] = dp[dp[node][i-1]][i-1];
                }
            }
        }
    }

    public static int getLCA(int node1, int node2){
        if (depth[node1] < depth[node2]){
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        for(int i = log - 1; i >= 0; i--){
            if (depth[node1] - (1 << i) >= depth[node2]){
                node1 = dp[node1][i];
            }
        }

        if (node1 == node2) return node1;

        for(int i = log - 1; i >= 0; i--){
            if (dp[node1][i] != dp[node2][i]){
                node1 = dp[node1][i];
                node2 = dp[node2][i];
            }
        }
        return dp[node1][0];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());



        tree = new ArrayList[n+1];
        for(int i = 1; i <= n ; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        int root = 1;
        preprocess(n, root);

        int m = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            System.out.println(getLCA(n1, n2));
        }

    }
}
