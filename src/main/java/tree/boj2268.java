package tree;

import java.util.*;

public class boj2268 {

    public static long[] tree;
    public static int leafStartIndex;

    public static void initTree(int n){
        int height = (int) Math.ceil(Math.log(n)/ Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        tree = new long[treeSize];

        leafStartIndex = treeSize/2;
    }

    public static void buildTree(){
        for(int i = leafStartIndex - 1; i >= 1; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public static long sumTree(int startIndex, int endIndex){
        startIndex += leafStartIndex - 1;
        endIndex += leafStartIndex - 1;

        long sum = 0;

        while (startIndex <= endIndex){
            if (startIndex % 2 == 1) sum += tree[startIndex];
            if (endIndex % 2 == 0) sum += tree[endIndex];

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
            tree[updateIndex] = tree[updateIndex*2] + tree[updateIndex*2+1];
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        initTree(n);
        for(int i = 0 ; i < n; i++){
            tree[i+leafStartIndex] = 0;
        }
        buildTree();

        for(int i = 0 ; i < m; i++){
            int function = sc.nextInt();
            int a = sc.nextInt();
            long b = sc.nextInt();

            // 0 -> sum
            if (function == 0){
                int startIndex = Math.min(a, (int) b);
                int endIndex = Math.max(a, (int) b);
                System.out.println(sumTree(startIndex, endIndex));
            }
            // 1 -> modify
            if (function == 1){
                updateTree(a, b);
            }
        }
    }
}
