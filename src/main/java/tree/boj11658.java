package tree;

import java.util.*;

public class boj11658 {

    public static int[] tree;
    public static int leafStartIndex;

    public static void initTree(int leafSize){
        int height = (int) Math.ceil(Math.log(leafSize)/Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, height);

        tree = new int[treeSize];

        leafStartIndex = treeSize/2;
    }

    public static void buildTree(){
        for(int i = leafStartIndex - 1; i>= 1; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public static int sumTree(int startIndex, int endIndex){
        startIndex += leafStartIndex;
        endIndex += leafStartIndex;

        int sum = 0;

        while (startIndex <= endIndex){
            if (startIndex % 2 == 1) sum += tree[startIndex];
            if (endIndex % 2 == 0) sum += tree[endIndex];
            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }

        return sum;
    }

    public static void updateTree(int updateIndex, int value){
        updateIndex += leafStartIndex;
        tree[updateIndex] = value;

        while (updateIndex > 1){
            updateIndex /= 2;
            tree[updateIndex] = tree[updateIndex*2] + tree[updateIndex*2+1];
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int leafSize = n*n;
        initTree(leafSize);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                tree[i*n + j + leafStartIndex] = sc.nextInt();
            }
        }
        buildTree();

        for(int i = 0 ; i < m; i++){
            int w = sc.nextInt();
            if (w == 1){
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();

                int sum = 0;
                for(int x = x1; x <= x2; x++){
                    int startIndex = (x - 1) * n + (y1-1);
                    int endIndex = (x - 1) * n + (y2-1);
                    sum += sumTree(startIndex, endIndex);
                }

                System.out.println(sum);
            }
            else{
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();

                int updateIndex = (x - 1) * n + (y-1);
                updateTree(updateIndex, c);


            }
        }
    }
}



/*
                             64
            24                               40
    10              14               18             22
   3, 7,           5, 9,           7, 11,          9, 13
1, 2, 3, 4,     2, 3, 4, 5,     3, 4, 5, 6,     4, 5, 6, 7


23  24
2,2 ~ 2,4
3,2 ~ 3,4

 */
