package dfs;

import java.util.*;

public class boj2606 {
    static ArrayList<Integer>[] graph;

    public static void dfs(int n){

        int cnt = 0;

        boolean[] visited = new boolean[n+1];
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        while(!stack.isEmpty()){
            int cur = stack.pop();

            if (!visited[cur]){
                visited[cur] = true;
                cnt++;

                for(int adj : graph[cur]){
                    if (!visited[adj]){
                        stack.push(adj);
                    }
                }
            }
        }
        System.out.println(cnt-1);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(n);


    }
}
