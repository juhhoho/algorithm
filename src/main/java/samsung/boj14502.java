package samsung;

import java.util.*;

public class boj14502 {

    public static int[][] map;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static ArrayList<Loc> candidateWalls;

    public static class Loc{
        int cr, cc;
        public Loc(int cr, int cc){
            this.cr = cr;
            this.cc = cc;
        }
    }

    public static ArrayList<Loc[]> getThreeLocs(){
        ArrayList<Loc[]> temp = new ArrayList<>();
        for(int i = 0; i < candidateWalls.size(); i++){
            for(int j = i+1; j < candidateWalls.size(); j++){
                for(int k = j + 1; k < candidateWalls.size(); k++){
                    Loc[] locs = new Loc[3];
                    locs[0] = candidateWalls.get(i);
                    locs[1] = candidateWalls.get(j);
                    locs[2] = candidateWalls.get(k);
                    temp.add(locs);
                }
            }
        }
        return temp;
    }

    public static int spreadVirus(int[][] tempMap, int n, int m){
        int days = 0;
        while(days < n+m){

            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= m; j++){
                    if (tempMap[i][j] == 2){
                        for(int k = 0; k < 4; k++){
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (1 <= nr && nr <= n && 1 <= nc && nc <= m && tempMap[nr][nc] == 0){
                                tempMap[nr][nc] = 2;
                            }
                        }
                    }
                }
            }
            days++;
        }

        int zero = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if (tempMap[i][j] == 0) zero++;
            }
        }
        return zero;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


        candidateWalls = new ArrayList<>();

        map = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j<= m; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0) candidateWalls.add(new Loc(i, j));
            }
        }

        ArrayList<Loc[]> locs = getThreeLocs();

        int res = 0;
        for(int i = 0 ; i < locs.size(); i++){
            Loc[] locList = locs.get(i);

            int[][] tempMap = new int[n+1][m+1];
            for (int q = 1; q <= n ; q++) {
                tempMap[q] = map[q].clone();  // 배열을 복사하여 새로운 배열에 저장
            }

            for(int j=0; j < 3; j++){
                tempMap[locList[j].cr][locList[j].cc] = 1;
            }
            res = Math.max(spreadVirus(tempMap, n, m), res);

        }
        System.out.println(res);

    }
}
