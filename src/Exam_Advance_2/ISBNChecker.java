package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class ISBNChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();
        if (isbn.length() != 10) {
            System.out.println("Số ISBN không hợp lệ.");
            return;
        }
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            char digitChar = isbn.charAt(i);

            if (!Character.isDigit(digitChar)) {
                System.out.println("Số ISBN không hợp lệ.");
                return;
            }
            int digit = Character.getNumericValue(digitChar);
            stack.push(digit);
            sum += (i + 1) * digit;
        }
        if (sum % 11 == 0) {
            System.out.println("Số ISBN hợp lệ.");
        } else {
            System.out.println("Số ISBN không hợp lệ.");
        }
    }
}
