package tree;

import java.util.*;

public class boj11505 {

    public static long[] tree;
    public static int leafStartIndex;

    public static void initTree(int n){
        int height = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        tree = new long[treeSize];

        leafStartIndex = treeSize/2;
    }

    public static void buildTree(){
        for(int i = leafStartIndex - 1; i >= 1; i--){
            tree[i] = (tree[i*2] * tree[i*2+1]) % 1000000007;
        }
    }

    public static void updateTree(int updateIndex, long value){
        updateIndex += leafStartIndex - 1;

        tree[updateIndex] = value;

        while(updateIndex > 1){
            updateIndex /= 2;
            tree[updateIndex] = (tree[updateIndex*2] * tree[updateIndex*2+1]) % 1000000007;
        }
    }

    public static long getMultiplyTree(int startIndex, int endIndex){
        startIndex += leafStartIndex - 1;
        endIndex += leafStartIndex - 1;

        long multiply = 1;
        while (startIndex <= endIndex){
            if (startIndex % 2 == 1)
                multiply = (multiply * tree[startIndex])% 1000000007;
            if (endIndex % 2 == 0)
                multiply = (multiply * tree[endIndex])% 1000000007;

            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }
        return multiply % 1000000007;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        initTree(n);
        for(int i = 0; i < n; i++){
            tree[i+leafStartIndex] = sc.nextLong();
        }
        buildTree();

        int mCnt = 0;
        int kCnt = 0;

        while(mCnt < m || kCnt < k){
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            // update
            if (a == 1){
                mCnt++;
                updateTree(b, c);
            }
            // multiply
            if (a == 2){
                kCnt++;
                System.out.println(getMultiplyTree(Math.min(b, (int) c), Math.max(b, (int) c)));
            }
        }
    }
}
/*

                            0
            24                              0
    2               12              0               0
1       2       6       4       5       0       0       0


 */