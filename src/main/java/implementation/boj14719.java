package implementation;

import java.util.*;

public class boj14719 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();

        int[][] map = new int[h][w];

        for(int j = 0; j < w; j++){
            int wall = sc.nextInt();
            if (wall == 0) {
                for(int i = h-1; i >= 0; i--) map[i][j] = 1;
            }
            else{
                for(int i = 0; i < h-wall; i++) map[i][j] = 1;
                for(int i = h-wall; i <h; i++){
                    map[i][j] = 2;
                }
            }
        }
        // 1 -> 비, 2 -> 벽
        int sum = 0;
        for(int[] arr : map){
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < arr.length; i++){
                if (sb.isEmpty()){
                    if (arr[i] == 2) {
                        sb.append("(");
                    }
                }
                else{
                    if (arr[i] == 1) {
                        sb.append("*");
                    }
                    else {
                        sb.append("),");
                        sb.append("(");
                    }
                }
            }

            if (!sb.toString().contains(",")) continue;
            String[] split = sb.toString().split(",");
            for(String sp: split){
                if (sp.charAt(0) == '(' && sp.charAt(sp.length()-1) == ')'){
                    sum += sp.length()-2;
                }
            }
        }
        System.out.println(sum);
    }
}

/*
1 1 1 1 * 1 1 1 -> stack * 1 1 1
* 1 1 * * 1 1 1 ->
* 1 * * * 1 1 *
* * * * * * * *


 */