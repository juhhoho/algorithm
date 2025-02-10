package bfs;

import java.util.*;

public class boj22861 {
    static int[] seq;

    public static void func(int start, int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[n+1];

        while(!queue.isEmpty()){
            int cur = queue.poll();
            if (!visited[cur]){
                visited[cur] = true;

                int next = seq[cur];
                if(!visited[next]){
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();
        for(int t = 0 ; t < test; t++){
            int n = sc.nextInt();

            seq = new int[n+1];
            for(int i = 1; i <= n; i++){
                seq[i] = sc.nextInt();
            }

            for(int i = 1; i <= n; i++){
                func(i, n);
                System.out.println();
            }



        }
    }
}

/*
3
1
3

[4]: 4 -> 7 -> 6 -> 4
3
4
6

 */