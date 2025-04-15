package dijkstra;

import java.util.*;

public class boj4485 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Node{
        int index, cost;
        public Node(int i, int c){
            this.index = i;
            this.cost = c;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int order = 1;
        while(true){
            int n = sc.nextInt();
            if (n == 0) break;

            int[][] map = new int[n+1][n+1];
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            List<Node>[] graph = new ArrayList[n*n+1];
            for(int i = 1; i<= n*n; i++) graph[i] = new ArrayList<>();

            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    int cr = i;
                    int cc = j;
                    int curIndex = (cr-1)*n + cc;
                    for(int k = 0; k < 4; k++) {
                        int nr = cr + dr[k];
                        int nc = cc + dc[k];
                        if (1 <= nr && nr <= n && 1 <= nc && nc <= n) {
                            int nextIndex = (nr - 1) * n + nc;
                            graph[curIndex].add(new Node(nextIndex, map[nr][nc]));
                        }
                    }
                }
            }

            int[] distance = new int[n*n+1];
            Arrays.fill(distance, 10000);
            distance[1] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{return o1.cost-o2.cost;});
            pq.offer(new Node(1, 0));

            while(!pq.isEmpty()){
                Node curNode = pq.poll();

                if (curNode.cost < distance[curNode.index]) continue;

                for(Node nextNode: graph[curNode.index]){
                    if (distance[curNode.index] + nextNode.cost < distance[nextNode.index]){
                        distance[nextNode.index] = distance[curNode.index] + nextNode.cost;
                        pq.offer(new Node(nextNode.index, distance[nextNode.index]));
                    }
                }
            }

            System.out.printf("Problem %d: %d\n", order++, map[1][1] + distance[n*n]);
        }
    }
}
