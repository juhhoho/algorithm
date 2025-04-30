package implementation;

import java.util.*;

public class boj1697 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});

        boolean[] visited = new boolean[100001];
        visited[n] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if (cur[0] == m) {
                System.out.println(cur[1]);
                break;
            }

            if (cur[0]-1 >= 0 && !visited[cur[0]-1]) {
                queue.offer(new int[]{cur[0]-1, cur[1]+1});
                visited[cur[0]-1] = true;
            }
            if (cur[0]+1 <= 100000 && !visited[cur[0]+1]) {
                queue.offer(new int[]{cur[0]+1, cur[1]+1});
                visited[cur[0]+1] = true;
            }
            if (cur[0]*2 <= 100000 && !visited[cur[0]*2]) {
                queue.offer(new int[]{cur[0]*2, cur[1]+1});
                visited[cur[0]*2] = true;
            }
        }
    }
}
