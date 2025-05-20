package implementation;

import java.util.*;

public class boj5568 {
    static int n, k;
    static int[] arr;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        boolean[] used = new boolean[n];
        backtrack(new StringBuilder(), 0, 0, used);

        System.out.println(set.size());
    }

    public static void backtrack(StringBuilder sb, int depth, int cnt, boolean[] used){
        if (cnt == k){
            set.add(sb.toString());
            return;
        }

        for(int i = 0; i < n; i++){
            if (!used[i]){
                used[i] = true;
                int originalLength = sb.length();
                sb.append(arr[i]);
                backtrack(sb, depth+1, cnt+1,used);
                sb.setLength(originalLength);
                used[i] = false;
            }
        }
    }
}
