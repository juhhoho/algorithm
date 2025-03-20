package tree;

import java.util.*;

public class boj11437 {

    static int n, LOG;
    static ArrayList<Integer>[] tree;
    static int[][] dp; // dp[node][k] : node의 2^k번째 조상
    static int[] depth;

    public static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        depth[root] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : tree[cur]) {
                if (depth[next] == -1) {  // 방문하지 않은 노드
                    depth[next] = depth[cur] + 1;
                    dp[next][0] = cur;  // 바로 위 부모 저장
                    queue.offer(next);
                }
            }
        }
    }
    /*
    LOG: 몇번째 조상까지를 고려해야하는지에 대한 변수
    예를 들어 전체 노드가 10개라고 하자.
    그러면 해당 트리에서 가능한 높이는 최대 10이 된다.
    우리는 2^k 만큼 위로 점프를 할 수 있어야 하는데, k가 0,1,2,3 총 4개가 나온다.
    k가 4일 때부터는 고려할 필요가 없는데 2^4 = 16인데 트리의 최대 높이가 10이므로 16만큼 점프할 일이 없기 떄문이다.

    dp[node][k]의 의미는
    node 기준 k번째 조상인 노드이다.
    점화식으로 나타내면 dp[node][k] = dp[dp[node][k-1]][k-1]

    depth 테이블은 두 노드의 depth를 맞추기 위함이다.

     */

    public static void preprocess() {
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        dp = new int[n + 1][LOG];
        depth = new int[n + 1];
        Arrays.fill(depth, -1);

        bfs(1);  // 루트 노드는 1번이라고 가정

        // DP 테이블 채우기 (점화식 적용)
        for (int k = 1; k < LOG; k++) {
            for (int node = 1; node <= n; node++) {
                if (dp[node][k - 1] != 0) {
                    dp[node][k] = dp[dp[node][k - 1]][k - 1];
                }
            }
        }
    }

    public static int getLCA(int node1, int node2) {
        if (depth[node1] < depth[node2]) {  // 항상 node1가 더 깊도록 스왑
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        // 깊이 맞추기
        for (int k = LOG - 1; k >= 0; k--) {
            int power = (int) Math.pow(2, k);  // 2^k 계산
            if (depth[node1] - power >= depth[node2]) {
                node1 = dp[node1][k];
            }
        }

        // LCA 찾기
        if (node1 == node2) return node1;

        for (int k = LOG - 1; k >= 0; k--) {
            if (dp[node1][k] != dp[node2][k]) {
                node1 = dp[node1][k];
                node2 = dp[node2][k];
            }
        }
        return dp[node1][0];  // 바로 위 부모가 LCA
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        preprocess();

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            System.out.println(getLCA(node1, node2));
        }
    }
}



//public class boj11437 {
//
//    public static Node[] tree;
//    public static ArrayList<Integer>[] graph;
//
//    public static class Node{
//        int parent, depth;
//        public Node(int parent, int depth) {
//            this.parent = parent;
//            this.depth = depth;
//        }
//    }
//
//    public static void bfs(int n){
//        Queue<int[]> queue = new LinkedList<>();
//        // int[]{index, parent, depth}
//        queue.offer(new int[]{1, 0, 1});
//
//        boolean[] visited = new boolean[n+1];
//
//        while(!queue.isEmpty()){
//            int[] cur = queue.poll();
//            int curIndex = cur[0];
//            int curParent = cur[1];
//            int curDepth = cur[2];
//
//            if (!visited[curIndex]){
//                visited[curIndex] = true;
//                tree[curIndex] = new Node(curParent, curDepth);
//
//                for(int adj : graph[curIndex]){
//                    if (!visited[adj]){
//                        queue.offer(new int[]{adj, curIndex, curDepth + 1});
//                    }
//                }
//            }
//        }
//
//
//    }
//
//    public static int getLCA(int node1, int node2){
//        int node1Depth = tree[node1].depth;
//        int node2Depth = tree[node2].depth;
//
//        while(node1Depth != node2Depth){
//            // node1이 더 깊게 박혀있을 때
//            if (node1Depth > node2Depth){
//                node1 = tree[node1].parent;
//                node1Depth = tree[node1].depth;
//            }
//            // node2가 더 깊게 박혀있을 떄
//            else{
//                node2 = tree[node2].parent;
//                node2Depth = tree[node2].depth;
//            }
//        }
//
//        while(node1 != node2){
//            node1 = tree[node1].parent;
//            node2 = tree[node2].parent;
//        }
//
//        return node1;
//    }
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        graph = new ArrayList[n+1];
//        for(int i = 1; i <= n; i++){
//            graph[i] = new ArrayList<>();
//        }
//
//        tree = new Node[n+1];
//        for(int i = 0 ; i < n-1; i++){
//            int node1 = sc.nextInt();
//            int node2 = sc.nextInt();
//
//            graph[node1].add(node2);
//            graph[node2].add(node1);
//        }
//
//        bfs(n);
//
//        int m = sc.nextInt();
//        for(int i = 0 ; i < m; i++){
//            int node1 = sc.nextInt();
//            int node2 = sc.nextInt();
//            System.out.println(getLCA(node1, node2));
//        }
//
//
//    }
//}
