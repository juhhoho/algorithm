package topology_sorting;
import java.util.*;

public class boj2623 {
    static ArrayList<Integer>[] graph;
    static int[] indegree;

    public static void topology_sorting(int n){
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            int cur = queue.poll();

            res.add(cur);
            for(int adj : graph[cur]){
                indegree[adj]--;
                if(indegree[adj] == 0){
                    queue.offer(adj);
                }
            }
        }
        if(res.size() < n){
            System.out.println(0);
        }
        else{
            for(int val: res){
                System.out.println(val);
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        indegree = new int[n+1];
        graph = new ArrayList[n+1];

        for(int i = 1; i<= n; i++){
            graph[i] = new ArrayList<>();
        }

        int m = sc.nextInt();
        for(int i = 0 ; i < m; i++){
            List<Integer> temp = new ArrayList<>();
            int size = sc.nextInt();
            for(int j = 0 ; j < size; j++){
                temp.add(sc.nextInt());
            }

            for(int j = 1; j < size; j++){
                int before = temp.get(j-1);
                int after = temp.get(j);

                graph[before].add(after);
                indegree[after]++;
            }
        }
        topology_sorting(n);


    }
}

/*
1 -> 4 -> 3

6 -> 2 -> 5 -> 4

3 -> 2

6 3
3 1 4 3
4 6 2 5 4
2 3 2

6 3
3 1 4 3
4 6 2 5 4
2 2 3


 */