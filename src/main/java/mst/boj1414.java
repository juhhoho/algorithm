package mst;

import java.util.*;

public class boj1414 {

    public static PriorityQueue<Edge> pq;
    public static int[] parent;

    public static class Edge{
        int v1, v2, c;

        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }

    public static int find(int node){
        if (node == parent[node]) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int n1, int n2){
        int r1 = find(n1);
        int r2 = find(n2);

        if (r1 != r2){
            parent[r1] = r2;
        }
    }

    public static ArrayList<Integer>[] graph;

    public static boolean checkChaining(int n, int start){

        if(start == -1) return false;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        queue.offer(start);

        int cnt = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(!visited[cur]){
                visited[cur] = true;
                cnt++;

                for(int adj : graph[cur]){
                    if(!visited[adj]){
                        queue.offer(adj);
                    }
                }
            }
        }
        return cnt != n;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] map = new int[n+1][n+1];
        int totalLength = 0;

        for(int i = 1 ; i <= n; i++){
            char[] charArray = sc.next().toCharArray();
            for(int j = 0 ; j < n; j++){
                if (65 <= (int) charArray[j] && (int) charArray[j] <= 90){
                    map[i][j+1] = (int) charArray[j] - 65 + 27;
                }
                else if (97 <= (int) charArray[j] && (int) charArray[j] <= 122){
                    map[i][j+1] = (int) charArray[j] - 97 + 1;
                }
                else{
                    map[i][j+1] = 0;
                }
                totalLength += map[i][j+1];
            }
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        parent = new int[n+1];
        for(int i = 1 ; i <= n; i++){
            parent[i] = i;
        }


        for(int i = 1; i<= n; i++){
            for(int j = 1 ; j <= n; j++){
                if (i < j) {
                    pq.offer(new Edge(i, j, Math.min(map[i][j] , map[j][i])));
                }
            }
        }

        int totalEdgeUsed = 0;
        int totalCost = 0;

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        int start = -1;

        while(!pq.isEmpty() && totalEdgeUsed < n-1){
            Edge cur = pq.poll();
            if (find(cur.v1) != find(cur.v2)){
                union(cur.v1, cur.v2);
                totalEdgeUsed++;
                totalCost += cur.c;

                graph[cur.v1].add(cur.v2);
                graph[cur.v2].add(cur.v1);

                if (start < 0){
                    start = cur.v1;
                }
            }
        }

        if (checkChaining(n, start)){
            System.out.println(-1);
        }
        else{
            System.out.println(totalLength - totalCost);
        }


    }
}

