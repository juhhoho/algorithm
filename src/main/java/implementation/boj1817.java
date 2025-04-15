package implementation;

import java.util.Scanner;

public class boj1817 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mSave = m;
        
        if (n >= 0){
            int[] books = new int[n];
            for(int i = 0; i < n; i++){
                books[i] = sc.nextInt();
            }
            
            int cnt = 1;
            for(int i = 0 ; i < n; i++){
                if (m >= books[i]) {
                    m -= books[i];
                }
                else{
                 cnt++;
                 m = mSave;
                 m -= books[i];
                }
            }
            System.out.println(cnt);
        }
        else{
            System.out.println(0);
        }
    }
}
