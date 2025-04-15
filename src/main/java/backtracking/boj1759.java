package backtracking;

import java.util.*;

public class boj1759 {

    static int l, c;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();

    static char[] mList = new char[]{'a', 'e', 'i', 'o', 'u'};


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        c = sc.nextInt();

        arr = new char[c];
        for(int i = 0 ; i < c;i++){
            arr[i] = sc.next().charAt(0);
        }
        Arrays.sort(arr);

        backtrack(0, "");

        System.out.println(sb);
    }

    public static void backtrack(int depth, String str){
        if (depth == c){
            if (isPromising(str)){
                sb.append(str).append("\n");
            }
            return;
        }

        backtrack(depth+1, str + arr[depth]);
        backtrack(depth+1, str);
    }

    public static boolean isPromising(String str){
        // 모음이 최소 1개
        // 서로 다른 자음이 최소 2개

        if (str.length() != l) return false;

        int mCnt = 0;
        Set<Character> set = new HashSet<>();


        for(int i = 0; i < str.length(); i++){
            char curChar = str.charAt(i);
            if (isM(curChar)){
                mCnt++;
            }
            else{
                set.add(curChar);
            }
        }
        return mCnt >= 1 && set.size() >= 2;

    }

    public static boolean isM(char ch) {
        for (int i = 0; i < 5; i++) {
            if (ch == mList[i]) {
                return true;
            }
        }
        return false;
    }
}
