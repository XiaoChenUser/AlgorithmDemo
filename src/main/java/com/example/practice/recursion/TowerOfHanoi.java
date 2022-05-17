package com.example.practice.recursion;

/**
 * 汉诺塔
 */
public class TowerOfHanoi {
    public static void hanoi(int n, String left, String middle, String right) {
        if (n <= 0) {
            return;
        }
        if (n == 1) {
            System.out.println("move " + n + " from " + left + " to " + right);
        }
        hanoi(n - 1, left, right, middle);
        System.out.println("move " + n + " from " + left + " to " + right);
        hanoi(n - 1, middle, left, right);
    }

    public static void main(String[] args) {
        TowerOfHanoi.hanoi(4, "left", "other", "right");
    }
}
