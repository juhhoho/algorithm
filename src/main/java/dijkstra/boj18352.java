package dijkstra;

import java.util.*;

public class boj18352 {

    static ArrayList<Integer>[] graph;

    public static void dijkstra(int target, int start, int n){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});

        boolean[] visited = new boolean[n+1];

        List<Integer> res = new ArrayList<>();

        while(!queue.isEmpty()){
            int[] curs = queue.poll();
            if (!visited[curs[0]]){
                visited[curs[0]] = true;
                if(curs[1] == target){
                    res.add(curs[0]);
                }
                for(int adj: graph[curs[0]]){
                    if(!visited[adj]){
                        queue.offer(new int[]{adj, curs[1] + 1});
                    }
                }
            }
        }
        if (res.isEmpty())
            System.out.println(-1);
        else{
            res.sort((i1, i2)->{if(i1 < i2) return -1; else return 1;});
            for(int i : res)
                System.out.println(i);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int s = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph[start].add(end);
        }

        dijkstra(k, s, n);
    }
}
