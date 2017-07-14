package com.linyoulin.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyoulin on 2017/7/13.
 */
public class KMP {
    public static void main(String[] args) {
        String a = "ababababababababab";
        String b = "ababacab";
        String c = "ababab";

        Integer[] indexes = indexOf2(a, c);

        System.out.println("indexes");
        for (int i = 0; i < indexes.length; i++) {
            System.out.print(indexes[i] + "  ");
        }

        getP(b);
        getP(c);
    }

    /**
     * 获取模板字符串的P数组,用于调整位置的,下标从0开始
     * @param template
     * @return
     */
    private static int[] getP(final String template) {
        if (template == null || "".equals(template)) return null;
        int[] p = new int[template.length()];
        p[0] = -1;

        int j = -1;

        char[] templateChar = template.toCharArray();

        for (int i = 1; i < templateChar.length; i++) {
            while (j > -1 && templateChar[j + 1] != templateChar[i]) {
                j = p[j];
            }
            if (templateChar[j + 1] == templateChar[i]) j++;
            p[i] = j;
        }
        System.out.print(template + "的p[]数组为:");
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }

        return p;
    }

    /**
     * 这个没有的考虑重叠的情况
     * @param a 文本
     * @param b 模式串
     * @return
     */
    private static Integer[] indexOf(final String a, final String b) {
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

    /**
     *
     * @param a 文本
     * @param b 模式串
     * @return
     */
    private static Integer[] indexOf2(final String a, final String b) {
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
                i = i - (pb[j] + 1);
                j = -1;
            }
            if (bChar[j + 1] == aChar[i]) {
                j++;
            }
        }
        return (Integer[]) indexList.toArray(new Integer[10]);
    }
}
