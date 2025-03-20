package tree;

import java.util.*;

public class boj11437re {

    public static ArrayList<Integer>[] tree;
    public static int[][] dp;
    public static int[] depth;
    public static int log;

    public static void bfs(int n){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 1}); // node, depth

        boolean[] visited = new boolean[n+1];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDepth = cur[1];

            if (!visited[curNode]){
                visited[curNode] = true;

                for(int next : tree[curNode]){
                    if (!visited[next]){
                        queue.offer(new int[]{next, curDepth+1});
                        depth[next] = curDepth + 1;
                        dp[next][0] = curNode;
                    }
                }
            }
        }
    }

    public static void preprocess(int n){
        log = (int) (Math.log(n) / Math.log(2)) + 1;
        dp = new int[n+1][log];
        depth = new int[n+1];

        bfs(n);

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

        for(int i = log -1; i >= 0; i--){
            if (depth[n1] - (1 << i) >= depth[n2]){
                n1 = dp[n1][i];
            }
        }

        if (n1 == n2) return n1;

        for(int i = log-1; i >= 0; i--){
            if (dp[n1][i] != dp[n2][i]){
                n1 = dp[n1][i];
                n2 = dp[n2][i];
            }
        }
        return dp[n1][0];
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < n-1; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        preprocess(n);

        int m = sc.nextInt();
        for(int i = 0 ; i < m; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            System.out.println(getLCA(n1, n2));
        }
    }
}


//    public static ArrayList<Integer>[] tree;
//    public static int log;
//    public static int[][] dp;
//    public static int[] depth;
//
//    public static void bfs(int n){
//        Queue<int[]> queue = new LinkedList<>();
//        boolean[] visited = new boolean[n+1];
//
//        // node, depth
//        queue.offer(new int[]{1, 1});
//        depth[1] = 1;
//
//        while(!queue.isEmpty()){
//            int[] cur = queue.poll();
//            int curNode = cur[0];
//            int curDepth = cur[1];
//
//            if(!visited[curNode]){
//                visited[curNode] = true;
//
//                for(int next: tree[curNode]){
//                    if (!visited[next]){
//                        queue.offer(new int[]{next, curDepth + 1});
//                        depth[next] = curDepth + 1;
//                        dp[next][0] = curNode;
//                    }
//                }
//            }
//        }
//    }
//
//    public static void preprocess(int n){
//        log = (int) (Math.log(n) / Math.log(2)) + 1;
//        dp = new int[n+1][log];
//        depth = new int[n+1];
//        Arrays.fill(depth, -1);
//
//        bfs(n);
//
//        for(int i = 1; i < log; i++){
//            for(int node = 1; node <= n; node++){
//                if (dp[node][i - 1] != 0){
//                    dp[node][i] = dp[dp[node][i-1]][i-1];
//                }
//            }
//        }
//        /*
//        ex. 노드가 7개 -> log = (int)(Math.log(7) / Math.log(2)) + 1 = 3
//                1
//               / \
//              2   3
//             /|   |\
//            4 5   6 7
//        node depth dp[node][0] dp[node][1](= dp[dp[node][0]][0]) dp[node][2](= dp[dp[node][1]][1])
//        1      1        -           -                                   -
//        2      2        1           -                                   -
//        3      2        1           -                                   -
//        4      3        2           1                                   -
//        5      3        2           1                                   -
//        6      3        3           1                                   -
//        7      3        3           1                                   -
//         */
//    }
//
//    public static int getLCA(int node1, int node2){
//        if (node1 < node2){
//            int temp = node1;
//            node1 = node2;
//            node2 = temp;
//        }
//
//        // 깊이 맞추기
//        for(int i = log - 1; i >= 0; i--){
//            if(depth[node1] - (1 << i) >= depth[node2]){
//                node1 = dp[node1][i];
//            }
//        }
//
//        // lca 찾기
//        if(node1 == node2) return node1;
//
//        for(int i = log - 1; i >= 0; i--){
//            if (dp[node1][i] != dp[node2][i]){
//                node1 = dp[node1][i];
//                node2 = dp[node2][i];
//            }
//        }
//
//        // 바로 위 부모가 lca
//        return dp[node1][0];
//    }
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        tree = new ArrayList[n+1];
//        for(int i = 1; i <= n; i++){
//            tree[i] = new ArrayList<>();
//        }
//
//        for(int i = 0; i < n - 1; i++){
//            int node1 = sc.nextInt();
//            int node2 = sc.nextInt();
//            tree[node1].add(node2);
//            tree[node2].add(node1);
//        }
//
//        preprocess(n);
//
//        int m = sc.nextInt();
//        for (int i = 0; i < m; i++) {
//            int node1 = sc.nextInt();
//            int node2 = sc.nextInt();
//            System.out.println(getLCA(node1, node2));
//        }
//
//
//    }
