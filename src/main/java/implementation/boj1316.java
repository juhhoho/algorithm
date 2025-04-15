package implementation;

import java.util.*;

public class boj1316 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int res = 0;

        for(int i = 0 ; i < n; i++){
            char[] chars = sc.next().toCharArray();
            Set<Character> set = new HashSet<>();
            set.add(chars[0]);
            boolean isDuplicated = false;

            for(int j = 1 ; j < chars.length; j++){
                if (chars[j-1] != chars[j]){
                    if (set.contains(chars[j])){
                        isDuplicated = true;
                        break;
                    }
                    set.add(chars[j]);
                }
            }
            if (!isDuplicated) res++;
        }
        System.out.println(res);
    }
}
