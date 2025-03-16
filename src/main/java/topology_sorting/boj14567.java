package topology_sorting;

import java.util.*;

public class boj14567 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


    }

}

/*
static ArrayList<Integer>[] graph;
    static int[] counts;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        counts = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        int m = sc.nextInt();

        for(int i = 0 ; i < m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s].add(e);
            counts[e]++;
        }

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if (counts[i] == 0)
                queue.offer(new int[]{i, 1});
        }

        int[] res = new int[n+1];
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int subject = cur[0];

            res[subject] = cur[1];

            for(int next: graph[subject]){
                counts[next]--;
                if(counts[next] == 0)
                    queue.offer(new int[]{next, cur[1] + 1});
            }
        }

        for(int i = 1; i <= n; i++){
            System.out.print(res[i] + " ");
        }
    }
 */
