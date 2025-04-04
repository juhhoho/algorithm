## 순열과 조합

조합이란 nCr로 표현을 많이 하는데, n개 중에 r개를 뽑은 경우의 수를 뜻함
조합과 비교되는 개념으로는 순열 nPr이 있으며, 이는 순서를 고려해 뽑는것을 말함.

여기서 순열보다는 조합이 좀 더 중요하고 출제빈도가 높음!

### 순열
nPr = n! / (n-r)! 
고등 수학 개념과 같으며
예시를 들어보면 5개중에 2개를 뽑을 때 5P2임.
5P2 = 5 * 4 이다.
ㅈㄴ 간단하네

### 조합
nCr = n! / ((n-r)! * r!)
순열에서 분모에 r!이 추가된다는 것만 같다.
r! 이 의미하는것은 순서를 무시해주는거야...

그런데 이는 수학적 이해이고, 알고리즘 적으로는 어떻게 접그해야할까?
이게 바로 dp와 점화식에 연결되기 때문에 중요함!!

### 조합에 대한 알고리즘적 접근
다시 돌아가 5개중에 3개를 선택하는 조합의 경우의 수를 푸는 문제를 가정해보자.

1. 모든 부분 문제가 해결된 상황이라고 가정하고 지금 문제 생각하기
모든 부분 문제가 해결되었다고 가정하는 것은 무슨 말일까?
지금 우리는 5개중 3개를 선택해야하는 문제를 풀고있다.
1, 2, 3, 4, 5
그런데 1~4까지의 수에 대해서는 일단 처리가 완료되었다고 가정한다는 것이다.
여기서 말하는 "처리"라 함은 뽑히고 안뽑히고를 의미하는게 아니라, 상태가 결정이 되었다는 것이다.
그럼 우리는 5에 대해서만 그 상태를 결정해주면 된다.

우리가 5에 대해서 결정할 수 있는 케이스는 뽑거나 안뽑거나 2개이다.
만약 우리가 5를 뽑는다면 1~4 중에 2개가 선택되어야 한다. -> 4C2
반면에 우리가 5를 뽑지 않는다면 1~4 중에 3개가 선택되어야 한다. -> 4C3
이 두가지 케이스는 합해서 100%를 커버해야 한다는 것이 핵심이다.

이를 수식으로 표현하면 다음과 같다.
5C3 = 4C3 + 4C2
이를 N개의 수 중에서 M개를 뽑는다고 한다면, 다음과 같이 일반화 가능하다.
nCm = (n-1)C(m) + (n-1)C(m-2)

최종적으로 이를 배열 형태로 나타낼 수 있다.
dp[i][j] = dp[i-1][j] + dp[i-1][j-1]


