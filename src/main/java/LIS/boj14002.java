package LIS;

import java.util.*;

public class boj14002 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();


        int maxIndex = 0;
        int maxValue = 1;
        for(int i = 0; i < n; i++){
            int cur = arr[i];

            int cntMaxIndex = i;
            for(int j = i-1; j >=0; j--){
                if (cur > arr[j] && dp[i] < dp[j] + 1){
                    cntMaxIndex = j;
                }
            }

            List<Integer> list = new ArrayList<>();
            if (cntMaxIndex == i) {
                dp[i] = 1;
                list.add(cur);
            }
            else{
                dp[i] = dp[cntMaxIndex]+1;
                list.addAll(graph[cntMaxIndex]);
                list.add(cur);
            }
            graph[i] = list;

            if (maxValue < list.size()){
                maxValue = list.size();
                maxIndex = i;
            }
        }

        System.out.println(graph[maxIndex].size());
        for(int value: graph[maxIndex]) System.out.print(value + " ");
    }
}
