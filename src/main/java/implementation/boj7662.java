package implementation;

import java.util.*;

public class boj7662 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int t = 0; t < test; t++){
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int n = sc.nextInt();
            for(int i = 0; i < n; i++){
                String action = sc.next();
                int number = sc.nextInt();

                if (action.equals("I")) {
                    if (!map.containsKey(number)) map.put(number, 1);
                    else map.put(number, map.get(number)+1);
                }
                else{
                    if (map.isEmpty()) continue;
                    // 최대 삭제
                    if (number == 1) {
                        if (map.get(map.lastKey()) == 1) map.remove(map.lastKey());
                        else{
                            map.put(map.lastKey(), map.get(map.lastKey())-1);
                        }
                    }
                    // 최소 삭제
                    else{
                        if (map.get(map.firstKey()) == 1) map.remove(map.firstKey());
                        else{
                            map.put(map.firstKey(), map.get(map.firstKey())-1);
                        }
                    }
                }
            }
            if (map.isEmpty()) System.out.println("EMPTY");
            else{
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
