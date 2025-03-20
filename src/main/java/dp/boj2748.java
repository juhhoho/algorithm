package dp;

import java.util.*;

public class boj2748 {

    public static HashMap<Integer, Long> hash;

    public static Long fibo(int n){

        if (hash.containsKey(n))
            return hash.get(n);

        hash.put(n, fibo(n-1) + fibo(n-2));
        return hash.get(n);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        hash = new HashMap<>();
        hash.put(1, 1L);
        hash.put(2, 1L);

        System.out.println(fibo(n));

    }
}
/*
1 2 3 4 5 6 7 8 9
1 1 2 3 5 8 13 21 34
 */