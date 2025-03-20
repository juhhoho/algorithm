package tree;

import java.util.*;

public class boj1275 {

    public static long[] tree;
    public static int leafStartIndex;

    public static void initTree(int n){
        int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        tree = new long[treeSize];

        leafStartIndex = treeSize / 2;

    }
    public static void buildTree(){
        for(int i = leafStartIndex-1; i >= 1; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public static long sumTree(int startIndex, int endIndex){
        startIndex += leafStartIndex - 1;
        endIndex += leafStartIndex - 1;

        long sum = 0;

        while(startIndex <= endIndex){
            if (startIndex % 2 == 1)
                sum += tree[startIndex];
            if (endIndex % 2 == 0)
                sum += tree[endIndex];

            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }
        return sum;
    }

    public static void updateTree(int updateIndex, long value){
        updateIndex += leafStartIndex - 1;
        tree[updateIndex] = value;

        while(updateIndex > 1){
            updateIndex /= 2;
            tree[updateIndex] = tree[updateIndex*2] + tree[updateIndex*2 + 1];
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        initTree(n);
        for(int i = 0; i < n; i++){
            tree[leafStartIndex + i] = sc.nextInt();
        }
        buildTree();

        for(int i = 0 ; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int a = sc.nextInt();
            long b = sc.nextLong();

            // 구간 합 x ~ y
            int startIndex = Math.min(x, y);
            int endIndex = Math.max(x, y);
            System.out.println(sumTree(startIndex, endIndex));

            // 구간 업데이트
            updateTree(a, b);
        }

    }

}
