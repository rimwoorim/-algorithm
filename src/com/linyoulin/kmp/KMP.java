package com.linyoulin.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyoulin on 2017/7/13.
 */
public class KMP {
    public static void main(String[] args) {
        String a = "ababababaababababccccccabababab";
        String b = "ababacab";
        String c = "ababab";

        Integer[] indexes = compare(a, c);

        for (int i = 0; i < indexes.length; i++) {
            System.out.print(indexes[i] + "  ");
        }
    }

    private static int[] getP(String template) {
        return new int[]{-1, -1, 0, 1, 2, 3};
//        return new int[]{-1, -1, 0, 1, 2, -1, -1, -1};
    }

    private static Integer[] compare(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int[] pb = getP(b);
        List indexList = new ArrayList();
        int j = -1;
        for (int i = 0; i < a.length(); i++) {
            while (j > -1 && j < b.length() - 1 && bChar[j + 1] != aChar[i]) {
                j = pb[j];
            }
            if (j == b.length() - 1) {
                indexList.add(i - b.length());
                j = -1;
            }
            if (bChar[j + 1] == aChar[i]) {
                j++;
            }
        }
        return (Integer[]) indexList.toArray(new Integer[10]);
    }
}
