## 세그먼트 트리
구간합, 최대, 최소
1. 트리 초기화하기

원본데이터가 이진트리의 리프노드가 됨
예를 들어 데이터의 개수가  7개라면
depth가 3일때 리프가 8개니까 거기의 리프에다 차곡차곡 넣어줌 
리프에서 부족한 부분은 0으로 채워준다면 총 트리의 크기는 2^3 * 2 - 1이고,
배열로는 2^4가 되겠지.

9개라면 depth가 4일때이고 총 배열의 크기는 2^5가 됨
 
그 다음 리프에서부터 루트로 구간합, 최대, 최소에 맞게 값을 넣어줌
leftChild/2 or rightChild/2가 부모이니까
* 구간합: arr[leftChild/2] = arr[leftChild] + arr[rightChild]
* 최대: arr[leftChild/2] = Math.max(arr[leftChild], arr[rightChild])
* 최소: arr[leftChild/2] = Math.min(arr[leftChild], arr[rightChild])

근데 이렇게하면 2로 나누어 떨어지는 구간에 대해서는 쉽게 구할 수 있는데, 
그렇지 않은 구간은 어떻게 구할까?

2. 질의값 구하기

주어진 질의 인덱스를 먼저 세그먼트 트리의 리프노드에 해당하는 인덱스로 변경
start_index = start + 2^k - 1
end_index = end + 2^k - 1
질의값 구하는 알고리즘
a. start_index % 2 == 1 일 때(즉, leftChild 의미) 해당 노드 선택
b. end_index % 2 == 0 일 때(즉, rightChild 의미) 해당 노드 선택
c. start_index depth 변경: start_index = (start_index + 1)/2
d. end_index depth 변경: end_index = (end_index - 1)/2
e. a~d를 반복하다가 end_index < start_index가 되면 종료


이걸 코드로 정리하면
static long[] tree;
static int leafStartIndex;

public void initTree(int n);
public void buildTree();
public sumTree(int startIndex, int endIndex);
public updateTree(int updateIndex, long value);

이렇게 2개의 전역변수와 4개의 함수가 핵심이다.

n개의 인풋 데이터를 모두 포함할 수 있는 완전 이진 트리 높이를 먼저 구하면 
n: 2 -> height: 2
n: 3~4 -> height: 3
n: 5 ~ 8 -> height: 4 이런식으로 될것이다.
int height = (int) Math.ceil(Math.log(n)/ Math.log(2)) + 1;
int treeSize = (int) Math.pow(2, height);
tree = new long[treeSize];
leafStartIndex = treeSize/2;

나머지 로직은 boj 2268을 참고하자!!
