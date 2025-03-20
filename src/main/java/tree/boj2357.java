package tree;

import java.util.*;

public class boj2357 {

    public static long[] maxTree;
    public static long[] minTree;
    public static int leafStartIndex;

    public static void initTree(int n){
        int height = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        maxTree = new long[treeSize];
        minTree = new long[treeSize];

        leafStartIndex = treeSize/2;
    }

    public static void buildTree(){
        for(int i = leafStartIndex - 1; i >= 1; i--){
            maxTree[i] = Math.max(maxTree[i*2], maxTree[i*2+1]);
            minTree[i] = Math.min(minTree[i*2], minTree[i*2+1]);
        }
    }

    public static long getMaxTree(int startIndex, int endIndex){
        startIndex += leafStartIndex - 1;
        endIndex += leafStartIndex - 1;

        long maxValue = 0;

        while (startIndex <= endIndex){
            if (startIndex % 2 == 1)
                maxValue = Math.max(maxValue, maxTree[startIndex]);
            if (endIndex % 2 == 0)
                maxValue = Math.max(maxValue, maxTree[endIndex]);

            startIndex = (startIndex + 1)/2;
            endIndex = (endIndex - 1)/2;
        }
        return maxValue;
    }

    public static long getMinTree(int startIndex, int endIndex){
        startIndex += leafStartIndex - 1;
        endIndex += leafStartIndex - 1;

        long minValue = Long.MAX_VALUE;

        while (startIndex <= endIndex){
            if (startIndex % 2 == 1)
                minValue = Math.min(minValue, minTree[startIndex]);
            if (endIndex % 2 == 0)
                minValue = Math.min(minValue, minTree[endIndex]);

            startIndex = (startIndex + 1)/2;
            endIndex = (endIndex - 1)/2;
        }
        return minValue;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        initTree(n);
        for(int i = 0 ; i < n; i++){
            long inputValue = sc.nextLong();
            maxTree[leafStartIndex + i] = inputValue;
            minTree[leafStartIndex + i] = inputValue;
        }
        buildTree();

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            int startIndex = Math.min(a, b);
            int endIndex = Math.max(a, b);

            System.out.printf("%d %d\n", getMinTree(startIndex, endIndex), getMaxTree(startIndex, endIndex));
        }
    }
}
