package org.khasanof.introduction;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.introduction
 * @since 11/22/2024 10:56 PM
 */
public class StaticInitializerBlock {

    private static Boolean flag;
    private static final int B;
    private static final int H;

    static {
        flag = Boolean.TRUE;
        Scanner scanner = new Scanner(System.in);
        B = scanner.nextInt();
        H = scanner.nextInt();

        if (B <= 0 || H <= 0) {
            try {
                throw new Exception("Breadth and height must be positive");
            } catch (Exception e) {
                System.out.println(e);
                flag = Boolean.FALSE;
            }
        }
    }

    public static void main(String[] args) {
        if (flag) {
            int area = B * H;
            System.out.print(area);
        }
    }
}
