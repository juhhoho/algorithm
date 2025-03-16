package topology_sorting;

import java.util.*;

public class boj20119 {

    static int[] indegree;
    static ArrayList<Integer>[] graph;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        indegree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            char[] charArray = sc.nextLine().toCharArray();

            int after = charArray[charArray.length-1] - 48;
            indegree[after] = (charArray.length-1)/2;

            for(int j = 0 ; j < charArray.length-1; j++){
                if (charArray[j] != ' '){
                    int before = charArray[j]-48;
                    graph[before].add(after);
                }
            }
        }

        int k = sc.nextInt();
        pq = new PriorityQueue<>();
        for(int i = 0 ; i < k ; i++){
            pq.offer(sc.nextInt());
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            System.out.print(cur + " ");

            for(int adj : graph[cur]){
                indegree[adj]--;
                if(indegree[adj] == 0){
                    pq.offer(adj);
                }
            }
        }

        
    }
}
