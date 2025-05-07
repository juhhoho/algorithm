package implementation;

import java.util.*;

public class boj3075 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t = 0; t < test; t++){
            int n = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();

            List<Integer> people = new ArrayList<>();
            for(int i = 0; i < n; i++) people.add(sc.nextInt());

            int[][] map = new int[p+1][p+1];
            for(int i = 1; i <= p; i++){
                Arrays.fill(map[i], 1_000_000);
                map[i][i] = 0;
            }

            for(int i = 0; i < q; i++){
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int cost = sc.nextInt();
                map[v1][v2] = cost;
                map[v2][v1] = cost;
            }

            for(int k = 1; k <= p; k++){
                for(int i = 1; i <= p; i++){
                    for(int j = 1; j <= p; j++){
                        if (map[i][k] < 1_000_000 && map[k][j] < 1_000_000 && map[i][j] > map[i][k] + map[k][j]){
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }

            int minNode = Integer.MAX_VALUE;
            int minSum = Integer.MAX_VALUE;
            for(int i = 1; i <= p; i++){
                int curSum = 0;

                boolean continueFlag = false;
                for(int person: people){
                    int dist = map[i][person];
                    if (dist >= 1_000_000) {
                        continueFlag = true;
                        break;
                    }
                    curSum += (int) Math.pow(dist, 2);
                }
                if (continueFlag) continue;

                if (minSum > curSum){
                    minSum = curSum;
                    minNode = i;
                }
            }
            System.out.println(minNode + " " + minSum);
        }
    }
}
