package implementation;

import java.util.*;

public class boj6550 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.next();
            String t = sc.next();

            int t_idx = 0;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                char stdChar = s.charAt(i);
                while(t_idx < t.length() && stdChar != t.charAt(t_idx)){
                    t_idx++;
                }
                if (t_idx == t.length()) break;
                sb.append(t.charAt(t_idx));
                t_idx++;
            }

            if (sb.toString().equals(s)) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}
/*

1 ~ 4

1 2 3 4


 */