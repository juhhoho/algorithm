package operation;

import java.util.*;
/*
1. odd > even
2. big > small
3. use at least one

2

3
5

evenMul = 2

oddMul = 15

 */


public class boj21312 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int evenMul = 1;
        int oddMul = 1;

        for(int i = 0 ; i < 3; i++){
             int n = sc.nextInt();
             if (n % 2 == 0){
                 evenMul *= n;
             }
             else{
                 oddMul *= n;
             }
        }
        if (oddMul == 1){
            System.out.println(evenMul);
        }
        else{
            System.out.println(oddMul);
        }

    }
}
