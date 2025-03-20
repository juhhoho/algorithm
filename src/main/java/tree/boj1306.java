package tree;

import java.util.*;

public class boj1306 {

    public static int[] tree;
    public static int leafStartIndex;

    public static void initTree(int n){
        int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        tree = new int[treeSize];
        leafStartIndex = treeSize/2;
    }

    public static void buildTree(){
        for(int i = leafStartIndex-1; i >= 1; i--){
            tree[i] = Math.max(tree[i*2], tree[i*2+1]);
        }
    }

    public static int maxSubTree(int startIndex, int endIndex){
        startIndex += leafStartIndex-1;
        endIndex += leafStartIndex-1;

        int maxVal = 0;

        while(startIndex <= endIndex){
            if (startIndex % 2 == 1)
                maxVal = Math.max(maxVal, tree[startIndex]);
            if (endIndex % 2 == 0)
                maxVal = Math.max(maxVal, tree[endIndex]);

            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }

        return maxVal;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        initTree(n);
        for(int i = 0 ; i < n; i++){
            tree[leafStartIndex + i] = sc.nextInt();
        }
        buildTree();

        for(int i = m; i <= n-m+1; i++){
            System.out.printf("%d ", maxSubTree(i-m+1, i+m-1));
        }
    }
}
