package util;

import model.Person;
import model.Pool;

import java.util.Scanner;
import java.util.InputMismatchException;

public class InputValidator {

    public static void getValidTimeInput(Scanner scanner, Pool pool) {
        int time;
        while (true) {
            System.out.print("\n몇 시에 이용할 예정인가요? (숫자만 입력하세요.) ");
            try {
                time = scanner.nextInt();
                if (!pool.isOpen(time)) {
                    System.out.println("이용 가능 시간이 아닙니다. 다시 입력해주세요.");
                } else {
                    System.out.println("이용 가능합니다.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
                scanner.next();
            }
        }
    }

    public static Person getValidPersonInput(Scanner scanner) {
        String name = "";
        while (true) {
            System.out.print("\n이름을 입력하세요: ");
            name = scanner.next();
            if (name.isEmpty()) {
                System.out.println("\n이름을 입력해주세요.");
            } else {
                break;
            }
        }

        int experience = -1;
        while (true) {
            System.out.print("\n수영 경력(개월)을 입력하세요: ");
            try {
                experience = scanner.nextInt();
                if (experience < 0) {
                    System.out.println("경력은 0개월 이상의 숫자여야 합니다.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
                scanner.next();
            }
        }

        return new Person(name, experience);
    }

    public static int getValidIntInput(String prompt, Scanner scanner) {
        int result = -1;
        while (true) {
            System.out.print(prompt);
            try {
                result = scanner.nextInt();
                if (result != 0 && result != 1) {
                    System.out.println("잘못된 입력입니다. 0 또는 1을 입력해주세요.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
                scanner.next();
            }
        }
        return result;
    }
}