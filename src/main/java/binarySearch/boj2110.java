package binarySearch;

import java.util.*;

public class boj2110 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 집 개수
        long c = sc.nextLong(); // 공유기 개수, 집에 설치

        long[] arr = new long[n]; // 수직선 상 집 좌표
        Arrays.sort(arr);

    }
}
/*
1 2 * 4 * * * 8 9
그럼 1, 4, 9에 공유기를 설치해야 최대한 균일하게 설치가 가능함.
균일하게 어떻게 놓을건데?
포문돌면서 일단 하나의 위치를 잡아
그리고 제공되는 공유기 개수 -1 개에 대해서

 */