package backtracking;

import java.util.*;

public class boj15649 {
    static int n,m;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean[n+1];

        ArrayList<Integer> currentList = new ArrayList<>();
        backtrack(0, currentList);

        System.out.println(sb);
    }

    public static void backtrack(int depth, ArrayList<Integer> currentList){
        if (depth == m){
            for(int element : currentList){
                sb.append(element).append(" ");
            }
            sb.append("\n");
            return ;
        }
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                currentList.add(i);
                backtrack(depth+1, currentList);
                currentList.remove(currentList.size()-1);
                visited[i] = false;
            }
        }
    }
}

/*
즉 4개중에 2개를 고르면
13과 31읻 다름
 */
