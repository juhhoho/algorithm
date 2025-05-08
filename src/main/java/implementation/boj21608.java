package implementation;

import java.util.*;

public class boj21608 {

    static int[][] map;
    static List<int[]> list = new ArrayList<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int res = 0;

        map = new int[n][n];

        for(int i = 0; i < n*n; i++){
            int[] arr = new int[5];
            for(int j = 0; j < 5; j++) arr[j] = sc.nextInt();
            list.add(arr);
        }

        for(int i = 0; i < n*n; i++){
            int[] curs = list.get(i);
            List<int[]> sameLikeList = getSameLikeFromEmptyList(n, curs, getEmpty(n));

            int[] loc;
            if (sameLikeList.size() == 1){
                loc = sameLikeList.get(0);
            }
            else{
                List<int[]> samezeroList = getNearbyEmptyFromSameLikeList(n, sameLikeList);
                if (samezeroList.size() > 1){
                    samezeroList.sort((o1, o2)->{
                        if (o1[0] == o2[0]){
                            return o1[1] - o2[1];
                        }
                        else return o1[0] - o2[0];
                    });
                }
                loc = samezeroList.get(0);
            }
            map[loc[0]][loc[1]] = curs[0];
        }

        for(int[] curs: list){
            int cur = curs[0];
            int cnt = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (map[i][j] == cur){
                        for(int k = 0; k < 4; k++){
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (0 <= nr && nr < n && 0 <= nc && nc < n){
                                for(int p = 1; p <= 4; p++){
                                    if (map[nr][nc] == curs[p]) cnt++;
                                }
                            }
                        }
                    }
                }
            }
            switch (cnt){
                case 4: res += 1000; break;
                case 3: res += 100; break;
                case 2: res += 10; break;
                case 1: res += 1; break;
            }
        }
        System.out.println(res);

    }

    public static List<int[]> getNearbyEmptyFromSameLikeList(int n, List<int[]> sameLikeList){
        Map<int[], Integer> tempMap = new HashMap<>();
        for(int[] curIter: sameLikeList){
            int cnt = 0;
            for(int i = 0; i < 4; i++){
                int nr = curIter[0] + dr[i];
                int nc = curIter[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < n && map[nr][nc] == 0){
                    cnt++;
                }
            }
            tempMap.put(curIter, cnt);
        }

        List<int[]> res = new ArrayList<>();

        int max = -1;

        for (Map.Entry<int[], Integer> curIter : tempMap.entrySet()) {
            if (max < curIter.getValue()) max = curIter.getValue();
        }

        for(Map.Entry<int[], Integer> curIter: tempMap.entrySet()){
            if (curIter.getValue() == max) res.add(curIter.getKey());
        }

        return res;
    }

    public static List<int[]> getSameLikeFromEmptyList(int n, int[] curs, List<int[]> emptyList){
        Map<int[], Integer> tempMap = new HashMap<>();
        for(int i = 0; i < emptyList.size(); i++){
            int[] loc = emptyList.get(i);

            int cnt = 0;

            for(int k = 0; k < 4; k++){
                int nr = loc[0] + dr[k];
                int nc = loc[1] + dc[k];
                if (0 <= nr && nr < n && 0 <= nc && nc < n){
                    for(int p = 1; p <= 4; p++){
                        if (map[nr][nc] == curs[p]) {
                            cnt++;
                            break;
                        }
                    }
                }
            }
            tempMap.put(loc, cnt);
        }

        List<int[]> res = new ArrayList<>();

        int max = -1;

        for (Map.Entry<int[], Integer> curIter : tempMap.entrySet()) {
            if (max < curIter.getValue()) max = curIter.getValue();
        }

        for(Map.Entry<int[], Integer> curIter: tempMap.entrySet()){
            if (curIter.getValue() == max) res.add(curIter.getKey());
        }
        return res;

    }

    public static List<int[]> getEmpty(int n){
        List<int[]> emptyList = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (!visited[i][j] && map[i][j] == 0){
                    bfs(i, j, n, visited, emptyList);
                }
            }
        }
        return emptyList;
    }
    public static void bfs(int row, int col, int n, boolean[][] visited, List<int[]> emptyList){

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            emptyList.add(cur);

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < n && !visited[nr][nc] && map[nr][nc] == 0){
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }
}
