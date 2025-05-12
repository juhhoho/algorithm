package implementation;

import java.util.*;

public class boj16236 {

    static int[][] map;
    static int sharkSize = 2;
    static int eatCnt = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Fish{
        int size, distance;
        int[] loc;
        public Fish(int size, int distance, int[] loc){
            this.size = size;
            this.distance = distance;
            this.loc = loc;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        map = new int[n][n];

        int[] start = new int[2];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        map[start[0]][start[1]] = 0;

        int time = 0;
        boolean isFin = false;
        while(true){

            // 1. fishList 초기화
            List<Fish> fishList = new ArrayList<>();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (1 <= map[i][j] && map[i][j] <= 6){
                        int dist = getDistance(start, i, j, n);
                        if(dist > 0) fishList.add(new Fish(map[i][j], dist, new int[]{i, j}));
                    }
                }
            }

            
            // 2. shark size 기반으로 갈 수 있는 것중에 먹을 수 잇는(상어보다 작은) 물고기 탐색
            fishList.removeIf(fist -> fist.size >= sharkSize);

            // 2-1. 만약 상어 size 이슈로 갈 수 있는 목적지 없으면 time 반환하고 끝
            if (fishList.isEmpty()) {
                System.out.println(time);
                isFin = true;
                break;
            }

            // 3. 먹보 리스트에서 가장 가까운 것으로 추리기
            fishList.sort((o1, o2)->o1.distance - o2.distance);
            int minDistance = fishList.get(0).distance;
            fishList.removeIf(fish -> fish.distance > minDistance);

            // 4. 최근접이 1개 이상이라면 행 위, 열 왼 기준으로 정렬
            if(fishList.size() > 1){
                fishList.sort((o1, o2)->{
                    if (o1.loc[0] == o2.loc[0]){
                        return o1.loc[1] - o2.loc[1];
                    }
                    else return o1.loc[0] - o2.loc[0];
                });

            }

            // 5-1. 물고기 get
            Fish resFish = fishList.get(0);

            // 5-2. 물고기 먹고 상어 크기 갱신
            if (eatCnt == sharkSize - 1){
                eatCnt = 0;
                sharkSize++;
            }
            else if (eatCnt < sharkSize){
                eatCnt++;
            }

            // 5-3.먹은자리로 이동
            start = resFish.loc;
            map[start[0]][start[1]] = 0;


            // 시간 증가
            time += resFish.distance;

        }


    }


    public static int getDistance(int[] loc, int dest_row, int dest_col, int n){

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{loc[0], loc[1], 0});

        boolean[][] visited = new boolean[n][n];
        visited[loc[0]][loc[1]] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0] == dest_row && cur[1] == dest_col) return cur[2];


            for(int i = 0 ; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(inRange(nr, nc, n, visited)){
                    if (map[nr][nc] <= sharkSize){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, cur[2] + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static boolean inRange(int row, int col, int n, boolean[][] visited){
        return 0 <= row && row < n && 0 <= col && col < n && !visited[row][col];
    }

}
/*
[기본 조건]
- 아기상어 초기 크기: 2

[이동 규칙]
- 1초에 인접한 칸(상하좌우)으로 1칸 이동
- 이동 규칙 세부:
    - 자신보다 크기가 큰 물고기: 지나갈 수 없음
    - 자신보다 크기가 같거나 작은 물고기: 이동 가능
    - 자신보다 작은 물고기만 먹을 수 있음

[행동 규칙]
- 먹을 수 있는 물고기가 없으면 엄마 상어에게 구조 요청 (게임 종료)
- 먹을 수 있는 물고기가 1마리면 → 해당 물고기 먹으러 감
- 먹을 수 있는 물고기가 2마리 이상이면:
    1. 가장 가까운 물고기 선택 (최단 거리 기준)
    2. 거리가 같으면 → "가장 위"에 있는 물고기
    3. 그래도 같으면 → "가장 왼쪽"에 있는 물고기

[성장 조건]
- 자신의 크기와 같은 수의 물고기를 먹으면 → 크기 +1
- 예: 크기 2일 때 2마리 먹으면 크기 3 됨
 */