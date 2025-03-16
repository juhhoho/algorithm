package lca;

import java.util.*;

public class boj3584 {
    static int[] parent;
    static ArrayList<Integer>[] graph;

    public static int find(int node){
        if (node == parent[node]){
            return node;
        }
        else{
            return parent[node];
        }
    }

    // left: 부모, right: 자식
    public static void union(int node1, int node2){
        node1 = find(node1);
        node2= find(node2);

        if(node1 != node2){
            parent[node2] = node1;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int t = 0; t < test; t++){
            int n = sc.nextInt();

            parent = new int[n+1];

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < n - 1; i++){
                int p = sc.nextInt();
                int c = sc.nextInt();
                graph[p].add(c);
            }
            int o1 = sc.nextInt();
            int o2 = sc.nextInt();

            System.out.println(Arrays.toString(parent));
            System.out.println("o1 = " + o1);
            System.out.println("o2 = " + o2);
            System.out.println();
        }
    }
}

/*
1   2   3   4   5
3   0   2   3   1

3 -> 2
5 -> 1 -> 3 -> 2


 */