package implementation;

import java.util.*;

public class boj19874 {
    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        visited = new boolean[n+1];

        backtrack(n);
    }

    public static void backtrack(int n){
        if (list.size() == n){
            for(int ele: list) System.out.print(ele + " ");
            System.out.println();
        }

        for(int i = 1; i <= n; i++){
            if (!visited[i]){
                visited[i] = true;
                list.add(i);
                backtrack(n);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
}
