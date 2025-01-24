package topology_sorting;

import java.util.*;

public class boj9470 {
    static ArrayList<Integer>[] graph_adj;
    static ArrayList<Integer>[] graph_seq;
    static int[] indegree;

    public static void topology_sorting(int test_num, int n){

        graph_seq = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph_seq[i] = new ArrayList<>();
        }
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                queue.offer(new int[]{i, 1});
            }
        }

        while(!queue.isEmpty()){
            int[] curs = queue.poll();
            int cur_node = curs[0];
            int cur_seq = curs[1];
            if (cur_node == n){
                System.out.println(test_num + " " + cur_seq);
            }

            for(int adj: graph_adj[cur_node]){
                indegree[adj]--;

                graph_seq[adj].add(cur_seq);

                if(indegree[adj] == 0){
                    //queue.offer(adj);
                    int max = 0;
                    for(int val : graph_seq[adj])
                        max = Math.max(max, val);
                    int cnt = 0;
                    for(int val : graph_seq[adj]){
                        if (val == max) cnt++;
                    }
                    if (cnt > 1)
                        max++;
                    queue.offer(new int[]{adj, max});
                }
            }


        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t = 0 ; t < test; t++){
            int k = sc.nextInt();
            int n = sc.nextInt();
            indegree = new int[n+1];
            graph_adj = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph_adj[i] = new ArrayList<>();
            }

            int m = sc.nextInt();
            for(int i = 0; i < m; i++){
                int before = sc.nextInt();
                int after = sc.nextInt();
                graph_adj[before].add(after);
                indegree[after]++;
            }
            topology_sorting(k, n);


        }
    }
}
