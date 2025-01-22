## MST
0. static PriorityQueue<Edge> pq, static int[] parent, Edge{s, e, cost} 생성
1. 우선순위 큐에 주어지는 엣지 담음
2. 엣지의 가중치가 작은것 부터 시작
3. 해당 순서의 엣지를 구성하는 두 노드의 루트를 find해 두 값이 다르다면 union
4. 사용한 엣지의 개수가 n-1개가 될 때 까지 반복

## 주의할 점
union - find 과정에서..
두 노드의 루트를 find해야되는데
parent 값만으로 비교하는 실수가 잦음
