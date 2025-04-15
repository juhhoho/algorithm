import java.util.*;

class Solution {
    public static ArrayList<Integer>[] graph;  // 그래프(인접 리스트)
    public static int[] indegree;  // 각 노드의 진입 차수
    public static List<List<Integer>> result;  // 모든 위상 정렬 결과 저장
    public static int n;  // 노드 개수

    // 모든 위상 정렬 찾기 (DFS + 백트래킹)
    public static void allTopologicalSorts(List<Integer> currentOrder, boolean[] visited) {
        boolean allVisited = true;

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0 && !visited[i]) {  // 진입 차수가 0인 노드를 찾음
                allVisited = false;  // 아직 방문할 노드가 있음
                visited[i] = true;  // 방문 처리
                currentOrder.add(i);  // 현재 정렬에 추가

                // 이 노드를 방문했으므로 연결된 노드들의 진입 차수를 줄인다
                for (int next : graph[i]) {
                    indegree[next]--;
                }

                // 재귀 호출 (다음 노드 방문)
                allTopologicalSorts(currentOrder, visited);

                // 백트래킹 (원래 상태로 복구)
                for (int next : graph[i]) {
                    indegree[next]++;
                }
                visited[i] = false;
                currentOrder.remove(currentOrder.size() - 1);
            }
        }

        // 모든 노드를 방문한 경우, 결과 리스트에 추가
        if (allVisited) {
            result.add(new ArrayList<>(currentOrder));
        }
    }

    // 메인 함수: 그래프 생성 후 DFS + 백트래킹 수행
    public List<List<Integer>> solution(int n, int[][] results) {
        this.n = n;
        indegree = new int[n + 1];  // 진입 차수 배열 초기화
        graph = new ArrayList[n + 1];  // 그래프 초기화
        result = new ArrayList<>();  // 결과 저장 리스트

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : results) {
            int before = edge[0];
            int after = edge[1];
            graph[before].add(after);
            indegree[after]++;  // after 노드의 진입 차수 증가
        }

        // DFS + 백트래킹으로 모든 위상 정렬 찾기
        allTopologicalSorts(new ArrayList<>(), new boolean[n + 1]);

        return result;
    }

    // 테스트 코드
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {3, 4}};

        List<List<Integer>> allOrders = sol.solution(4, edges);

        for (List<Integer> order : allOrders) {
            System.out.println(order);
        }
    }
}
