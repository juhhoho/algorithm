package implementation;

import java.util.*;

public class boj5052 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t = 0; t < test; t++){
            int n = sc.nextInt();

            List<String> list = new ArrayList<>();
            for(int i = 0; i < n; i++) list.add(sc.next());

            list.sort((s1,s2)->{return s1.compareTo(s2);});

            boolean clear = true;

            for(int i = 1; i < list.size(); i++){
                String shortNumber = list.get(i-1);
                String longNumber = list.get(i);

                if (shortNumber.length() > longNumber.length()){
                    String temp = shortNumber;
                    shortNumber = longNumber;
                    longNumber = temp;
                }

                String subsOfLongNumber = longNumber.substring(0, shortNumber.length());

                if (shortNumber.equals(subsOfLongNumber)) {
                    clear = false;
                    break;
                }
            }
            if (!clear) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}
