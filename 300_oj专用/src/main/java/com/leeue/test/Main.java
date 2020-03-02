package com.leeue.test;

import java.util.Scanner;

/**
 * @author liyue
 * @date 2020-02-29 22:57
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a, b;

        while (sc.hasNext()) {
            a = sc.nextInt();
            b = sc.nextInt();

            System.out.println(a + b);
        }
    }
}
