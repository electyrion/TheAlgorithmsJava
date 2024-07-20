package com.thealgorithms.others;

import java.util.Scanner;

final class FloydTriangle {
    private FloydTriangle() {
    }

    public static void main(String[] args) {
        int r;
        int n;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number of rows which you want in your Floyd Triangle: ");
            r = sc.nextInt();
            n = 0;
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(++n + " ");
            }
            System.out.println();
        }
    }
}
