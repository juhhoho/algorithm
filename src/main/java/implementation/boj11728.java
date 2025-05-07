package implementation;

import java.util.*;

public class boj11728 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] arrA = new int[a];
        int[] arrB = new int[b];
        for(int i = 0; i < a; i++) arrA[i] = sc.nextInt();
        for(int i = 0; i < b; i++) arrB[i] = sc.nextInt();

        int idxA = 0, idxB = 0;

        while(idxA < a && idxB < b){
            if (arrA[idxA] < arrB[idxB]){
                System.out.print(arrA[idxA] + " ");
                idxA++;
            }
            else{
                System.out.print(arrB[idxB] + " ");
                idxB++;
            }
        }
        while(idxA < a ){
            System.out.print(arrA[idxA] + " ");
            idxA++;
        }
        while(idxB< b){
            System.out.print(arrB[idxB] + " ");
            idxB++;
        }
    }
}
